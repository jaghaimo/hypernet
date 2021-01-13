package hypernet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotInSystem implements MarketFilter {

    private String systemId;

    public MarketNotInSystem(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem == null) {
            return true;
        }
        return !starSystem.getId().equalsIgnoreCase(systemId);
    }
}
