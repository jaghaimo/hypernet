package hypernet.filter;

import java.util.List;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.helper.CollectionHelper;

public class MarketHasAdministrator implements MarketFilter {

    public boolean accept(MarketAPI market) {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionHelper.reduce(people, new PersonAdministrator());
        return !people.isEmpty();
    }
}
