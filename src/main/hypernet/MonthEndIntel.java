package hypernet;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class MonthEndIntel extends BaseIntelPlugin {

    private String message;

    public MonthEndIntel(String message) {
        this.message = message;
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addTitle("HyperNET Intel");
        indent(info);
        info.addPara(message, 3f, getBulletColorForMode(mode));
        unindent(info);
    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("hypernet", "board");
    }
}
