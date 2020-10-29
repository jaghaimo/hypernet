package hypernet.filter;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class SubmarketHasFleetMember implements SubmarketFilter {

    private String shipHullSpec;

    public SubmarketHasFleetMember(FleetMemberAPI f) {
        shipHullSpec = f.getHullSpec().getHullId();
    }

    public boolean accept(SubmarketAPI submarket) {
        for (FleetMemberAPI f : submarket.getCargo().getMothballedShips().getMembersListCopy()) {
            ShipHullSpecAPI hullSpec = f.getHullSpec();
            if (shipHullSpec.equals(hullSpec.getHullId())) {
                return true;
            }
        }

        return false;
    }
}
