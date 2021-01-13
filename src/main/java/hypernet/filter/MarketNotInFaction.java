package hypernet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotInFaction implements MarketFilter {

    private String factionId;

    public MarketNotInFaction(String factionId) {
        this.factionId = factionId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        return !market.getFactionId().equalsIgnoreCase(factionId);
    }
}
