package hypernet.filter;

import com.fs.starfarer.api.campaign.CampaignUIAPI.CoreUITradeMode;
import com.fs.starfarer.api.campaign.CoreUIAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.HintPanelAPI;

public class SubmarketIsAccessible implements SubmarketFilter {

    private DummyCoreUi dummyCoreUi;

    public SubmarketIsAccessible() {
        dummyCoreUi = new DummyCoreUi();
    }

    public boolean accept(SubmarketAPI submarket) {
        return submarket.getPlugin().isEnabled(dummyCoreUi);
    }

    private class DummyCoreUi implements CoreUIAPI {

        @Override
        public HintPanelAPI getHintPanel() {
            return null;
        }

        @Override
        public CoreUITradeMode getTradeMode() {
            return CoreUITradeMode.NONE;
        }
    }
}
