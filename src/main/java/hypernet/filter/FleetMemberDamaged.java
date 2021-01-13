package hypernet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.DialogOption;

public class FleetMemberDamaged implements FleetMemberFilter {

    private DialogOption option;

    public FleetMemberDamaged(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
            case SHIP_DAMAGED_NO:
                return !f.getHullSpec().isDHull();

            case SHIP_DAMAGED_ONLY:
                return f.getHullSpec().isDHull();

            default:
                return true;
        }
    }
}
