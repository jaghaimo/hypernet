package hypernet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotTagged implements MarketFilter {

    private String tag;

    public MarketNotTagged(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean accept(MarketAPI market) {
        if (market.hasTag(tag)) {
            return false;
        }

        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem == null) {
            return true;
        }

        return !starSystem.hasTag(tag);
    }
}
