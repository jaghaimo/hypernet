package hypernet.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;

import hypernet.filter.MarketFilter;
import hypernet.filter.MarketHasAdministrator;
import hypernet.filter.MarketHasOfficer;
import hypernet.filter.MarketNotHidden;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasCargoStack;
import hypernet.filter.SubmarketHasFleetMember;

public class MarketHelper {

    public static boolean has(MarketAPI market, CargoStackAPI cargoStack) {
        SubmarketFilter filter = new SubmarketHasCargoStack(cargoStack);
        List<SubmarketAPI> submarkets = getSubmarkets(market);
        CollectionHelper.reduce(submarkets, filter);
        return !submarkets.isEmpty();
    }

    public static boolean has(MarketAPI market, FleetMemberAPI fleetMember) {
        SubmarketFilter filter = new SubmarketHasFleetMember(fleetMember);
        List<SubmarketAPI> submarkets = getSubmarkets(market);
        CollectionHelper.reduce(submarkets, filter);
        return !submarkets.isEmpty();
    }

    public static boolean has(MarketAPI market, String personality) {
        MarketFilter filter = new MarketHasOfficer(personality);
        return filter.accept(market);
    }

    public static boolean has(MarketAPI market) {
        MarketFilter filter = new MarketHasAdministrator();
        return filter.accept(market);
    }

    public static List<MarketAPI> getMarkets() {
        List<MarketAPI> markets = Global.getSector().getEconomy().getMarketsCopy();
        List<MarketFilter> filters = FilterHelper.getBlacklistMarketFilters();
        filters.add(new MarketNotHidden());
        CollectionHelper.reduce(markets, filters);
        return markets;
    }

    public static List<SubmarketAPI> getSubmarkets() {
        return getSubmarkets(getMarkets());
    }

    public static List<SubmarketAPI> getSubmarkets(MarketAPI market) {
        return getSubmarkets(Arrays.asList(market));
    }

    public static List<SubmarketAPI> getSubmarkets(List<MarketAPI> markets) {
        List<SubmarketAPI> submarkets = new ArrayList<>();
        for (MarketAPI market : markets) {
            submarkets.addAll(market.getSubmarketsCopy());
        }
        return submarkets;
    }

    public static void updateCargoPrePlayerInteraction(SubmarketAPI s) {
        try {
            ((BaseSubmarketPlugin) s.getPlugin()).updateCargoPrePlayerInteraction();
        } catch (Exception exception) {
        }
    }
}
