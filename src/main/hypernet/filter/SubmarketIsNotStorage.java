package hypernet.filter;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;

public class SubmarketIsNotStorage implements SubmarketFilter {

    public boolean accept(SubmarketAPI submarket) {
        String specId = submarket.getSpecId();
        return !Submarkets.SUBMARKET_STORAGE.equals(specId);
    }
}
