package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.HypernetIntel;
import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasFleetMember;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;
import hypernet.subject.ShipSubject;

public class ShipIntelProvider implements IntelProvider {

    private FleetMemberAPI fleetMember;

    public ShipIntelProvider(FleetMemberAPI f) {
        fleetMember = f;
    }

    @Override
    public IntelList provide(FilterManager filterManager) {
        IntelList intels = new IntelList();
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        SubmarketFilter filter = new SubmarketHasFleetMember(fleetMember);
        CollectionHelper.reduce(submarkets, filter);
        for (MarketAPI market : MarketHelper.extractMarkets(submarkets)) {
            ShipSubject subject = new ShipSubject(fleetMember, market);
            intels.add(new HypernetIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    @Override
    public String getDescription() {
        ShipSubject subject = new ShipSubject(fleetMember, null);
        return subject.getIntelTitle();
    }
}
