package hypernet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.DialogOption;

public class FleetMemberSize implements FleetMemberFilter {

    private DialogOption option;

    public FleetMemberSize(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
            case SHIP_SIZE_FRIGATE:
                return f.isFrigate();

            case SHIP_SIZE_DESTROYER:
                return f.isDestroyer();

            case SHIP_SIZE_CRUISER:
                return f.isCruiser();

            case SHIP_SIZE_CAPITAL:
                return f.isCapital();

            default:
                return false;
        }
    }
}
