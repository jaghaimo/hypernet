package hypernet.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.IntelSubject;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasFleetMember;
import hypernet.subject.ShipSubject;

public class ShipIntelProvider extends SubmarketProvider {

    private FleetMemberAPI fleetMember;

    public ShipIntelProvider(FleetMemberAPI f) {
        fleetMember = f;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new SubmarketHasFleetMember(fleetMember);
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new ShipSubject(fleetMember, market);
    }
}
