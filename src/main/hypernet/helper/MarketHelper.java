package hypernet.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;

import hypernet.filter.MarketFilter;
import hypernet.filter.MarketIsDiscovered;
import hypernet.filter.MarketNotHidden;

public class MarketHelper {

    public static Set<MarketAPI> extractMarkets(List<SubmarketAPI> submarkets) {
        Set<MarketAPI> markets = new HashSet<>();
        for (SubmarketAPI submarket : submarkets) {
            markets.add(submarket.getMarket());
        }
        return markets;
    }

    public static List<MarketAPI> getMarkets() {
        List<MarketAPI> markets = Global.getSector().getEconomy().getMarketsCopy();
        List<MarketFilter> filters = FilterHelper.getBlacklistMarketFilters();
        if (ConfigHelper.marketHasToBeNotHidden()) {
            filters.add(new MarketNotHidden());
        }
        if (ConfigHelper.marketHasToBeDiscovered()) {
            filters.add(new MarketIsDiscovered());
        }
        CollectionHelper.reduce(markets, filters);
        return markets;
    }

    public static List<SubmarketAPI> getSubmarkets() {
        return getSubmarkets(getMarkets());
    }

    public static List<SubmarketAPI> getSubmarkets(List<MarketAPI> markets) {
        List<SubmarketAPI> submarkets = new ArrayList<>();
        for (MarketAPI market : markets) {
            List<SubmarketAPI> marketSubmarkets = market.getSubmarketsCopy();
            updateCargoPrePlayerInteraction(marketSubmarkets);
            submarkets.addAll(marketSubmarkets);
        }
        return submarkets;
    }

    private static void updateCargoPrePlayerInteraction(List<SubmarketAPI> submarkets) {
        for (SubmarketAPI submarket : submarkets) {
            updateCargoPrePlayerInteraction(submarket);
        }
    }

    private static void updateCargoPrePlayerInteraction(SubmarketAPI submarket) {
        try {
            ((BaseSubmarketPlugin) submarket.getPlugin()).updateCargoPrePlayerInteraction();
        } catch (Exception exception) {
        }
    }
}
