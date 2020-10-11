package hypernet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;

import hypernet.DialogOption;

public class FleetMemberCivilian implements FleetMemberFilter {

    private DialogOption option;

    public FleetMemberCivilian(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
            case SHIP_CIVILIAN_NO:
                return !f.getVariant().hasHullMod(HullMods.CIVGRADE);

            case SHIP_CIVILIAN_ONLY:
                return f.getVariant().hasHullMod(HullMods.CIVGRADE);

            default:
                return true;
        }
    }
}
