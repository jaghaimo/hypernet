package hypernet.subject;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import hypernet.IntelSubject;
import hypernet.helper.MarketHelper;

public class ShipSubject extends IntelSubject {

    private FleetMemberAPI ship;

    public ShipSubject(FleetMemberAPI s, MarketAPI m) {
        super(s.getHullSpec().getHullNameWithDashClass() + " " + s.getHullSpec().getDesignation(), m);
        ship = s;
    }

    @Override
    public boolean canAcquire() {
        return MarketHelper.canAcquire(market, ship);
    }

    @Override
    public String getIcon() {
        return ship.getHullSpec().getSpriteName();
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market, ship);
    }
}
