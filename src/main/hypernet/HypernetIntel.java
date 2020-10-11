package hypernet;

import java.util.Set;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class HypernetIntel extends BaseIntelPlugin {

    public final static String TAG = "HyperNET";

    private FactionAPI faction;
    private SectorEntityToken sectorEntityToken;
    private IntelSubject intelSubject;

    public HypernetIntel(FactionAPI f, SectorEntityToken s, IntelSubject i) {
        faction = f;
        sectorEntityToken = s;
        intelSubject = i;
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara(intelSubject.getIntelTitle(), getTitleColor(mode), 0f);
        bullet(info);
        info.addPara(intelSubject.getIntelInfo(), 3f, getBulletColorForMode(mode), Misc.getHighlightColor(),
                sectorEntityToken.getName(), intelSubject.getStarSystemName(""));
        unindent(info);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
    }

    @Override
    public String getIcon() {
        return intelSubject.getIcon();
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return sectorEntityToken;
    }

    @Override
    public FactionAPI getFactionForUIColors() {
        return faction;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(TAG);
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public String getKey() {
        return intelSubject.getKey();
    }
}
