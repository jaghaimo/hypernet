package hypernet.filter;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.helper.CollectionHelper;

public class SubmarketHasFleetMember implements SubmarketFilter {

    private FleetMemberFilter filter;

    public SubmarketHasFleetMember(FleetMemberAPI fleetMember) {
        filter = new FleetMembersHasMember(fleetMember);
    }

    public boolean accept(SubmarketAPI submarket) {
        List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
        CollectionHelper.reduce(fleetMembers, filter);
        return !fleetMembers.isEmpty();
    }
}
