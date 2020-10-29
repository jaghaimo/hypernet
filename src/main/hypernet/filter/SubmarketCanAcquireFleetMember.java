package hypernet.filter;

import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class SubmarketCanAcquireFleetMember implements SubmarketFilter {

    private FleetMemberAPI fleetMember;

    public SubmarketCanAcquireFleetMember(FleetMemberAPI fleetMember) {
        this.fleetMember = fleetMember;
    }

    public boolean accept(SubmarketAPI submarket) {
        return !submarket.getPlugin().isIllegalOnSubmarket(fleetMember, TransferAction.PLAYER_BUY);
    }
}
