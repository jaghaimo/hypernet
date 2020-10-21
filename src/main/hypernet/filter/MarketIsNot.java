package hypernet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketIsNot implements MarketFilter {

    private String marketId;

    public MarketIsNot(String marketId) {
        this.marketId = marketId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        return !market.getId().equalsIgnoreCase(marketId);
    }
}
