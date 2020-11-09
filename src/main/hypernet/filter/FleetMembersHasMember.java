package hypernet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMembersHasMember implements FleetMemberFilter {

    private String shipHullSpecId;

    public FleetMembersHasMember(FleetMemberAPI f) {
        shipHullSpecId = f.getHullSpec().getHullId();
    }

    public boolean accept(FleetMemberAPI f) {
        String hullSpecId = f.getHullSpec().getHullId();
        return shipHullSpecId.equals(hullSpecId);
    }
}
