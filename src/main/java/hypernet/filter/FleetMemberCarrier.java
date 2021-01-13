package hypernet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.DialogOption;

public class FleetMemberCarrier implements FleetMemberFilter {

    private DialogOption option;

    public FleetMemberCarrier(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
            case SHIP_CARRIER_NO:
                return !f.isCarrier();

            case SHIP_CARRIER_ONLY:
                return f.isCarrier();

            default:
                return true;
        }
    }
}
