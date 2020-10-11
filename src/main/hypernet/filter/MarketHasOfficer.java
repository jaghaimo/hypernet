package hypernet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;

public class MarketHasOfficer implements MarketFilter {

    private String personality;

    public MarketHasOfficer(String p) {
        personality = p;
    }

    public boolean accept(MarketAPI market) {
        for (CommDirectoryEntryAPI entry : market.getCommDirectory().getEntriesCopy()) {
            PersonAPI person = (PersonAPI) entry.getEntryData();

            if (!person.getPostId().equals(Ranks.POST_MERCENARY)) {
                continue;
            }

            if (personality.equals(person.getPersonalityAPI().getId())) {
                return true;
            }
        }

        return false;
    }
}
