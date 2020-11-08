package hypernet.filter;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.helper.CollectionHelper;

public class MarketHasOfficer implements MarketFilter {

    private String personality;

    public MarketHasOfficer(String p) {
        personality = p;
    }

    public boolean accept(MarketAPI market) {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionHelper.reduce(people, Arrays.asList(new PersonOfficer(), new PersonPersonality(personality)));
        return !people.isEmpty();
    }
}
