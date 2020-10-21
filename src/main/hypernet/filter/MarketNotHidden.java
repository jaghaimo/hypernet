package hypernet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotHidden implements MarketFilter {

    public boolean accept(MarketAPI market) {
        return !market.isHidden();
    }
}
