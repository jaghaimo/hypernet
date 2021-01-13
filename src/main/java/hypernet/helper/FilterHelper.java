package hypernet.helper;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.Global;

import org.json.JSONArray;
import org.json.JSONObject;

import hypernet.filter.MarketFilter;
import hypernet.filter.MarketIsNot;
import hypernet.filter.MarketNotInFaction;
import hypernet.filter.MarketNotInSystem;
import hypernet.filter.MarketNotTagged;

public class FilterHelper {

    private static String FACTIONS = "data/config/hypernet/factions.csv";
    private static String MARKETS = "data/config/hypernet/markets.csv";
    private static String SYSTEMS = "data/config/hypernet/systems.csv";
    private static String TAGS = "data/config/hypernet/tags.csv";

    public static List<MarketFilter> getBlacklistMarketFilters() {
        List<MarketFilter> filters = new ArrayList<>();
        filters = getFactionFilters(filters);
        filters = getMarketFilters(filters);
        filters = getSystemFilters(filters);
        filters = getTagFilters(filters);
        return filters;
    }

    private static List<MarketFilter> getFactionFilters(List<MarketFilter> filters) {
        for (String factionId : getStrings(FACTIONS)) {
            filters.add(new MarketNotInFaction(factionId));
        }
        return filters;
    }

    private static List<MarketFilter> getMarketFilters(List<MarketFilter> filters) {
        for (String marketId : getStrings(MARKETS)) {
            filters.add(new MarketIsNot(marketId));
        }
        return filters;
    }

    private static List<MarketFilter> getSystemFilters(List<MarketFilter> filters) {
        for (String systemId : getStrings(SYSTEMS)) {
            filters.add(new MarketNotInSystem(systemId));
        }
        return filters;
    }

    private static List<MarketFilter> getTagFilters(List<MarketFilter> filters) {
        for (String tag : getStrings(TAGS)) {
            filters.add(new MarketNotTagged(tag));
        }
        return filters;
    }

    private static List<String> getStrings(String path) {
        List<String> strings = new ArrayList<>();
        try {
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", path, "hypernet");
            for (int i = 0; i < config.length(); i++) {
                JSONObject row = config.getJSONObject(i);
                strings.add(row.getString("id"));
            }
        } catch (Throwable throwable) {
        }
        return strings;
    }
}
