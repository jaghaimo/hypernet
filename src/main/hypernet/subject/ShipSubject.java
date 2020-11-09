package hypernet.subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasFleetMember;
import hypernet.helper.MarketHelper;

public class ShipSubject extends SubmarketSubject {

    private FleetMemberAPI ship;
    private Map<SubmarketAPI, List<FleetMemberAPI>> submarketsWithFleetMember;

    public ShipSubject(FleetMemberAPI s, MarketAPI m) {
        super(s.getHullSpec().getHullNameWithDashClass() + " " + s.getHullSpec().getDesignation(), m);
        ship = s;
    }

    @Override
    public boolean canAcquire() {
        return MarketHelper.canAcquire(market, ship);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        populate();
        addHeader(info, width);
        addBasicInfo(info);
        for (SubmarketAPI submarket : submarketsWithFleetMember.keySet()) {
            addSubmarket(info, submarket);
        }
    }

    @Override
    public String getIcon() {
        return ship.getHullSpec().getSpriteName();
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market, ship);
    }

    @Override
    protected int getEntityCount() {
        int count = 0;
        for (List<FleetMemberAPI> fleetMembers : submarketsWithFleetMember.values()) {
            count += fleetMembers.size();
        }
        return count;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new SubmarketHasFleetMember(ship);
    }

    @Override
    protected int getSubmarketCount() {
        return submarketsWithFleetMember.size();
    }

    private void populate() {
        submarketsWithFleetMember = new HashMap<>();
        for (SubmarketAPI submarket : getSubmarkets()) {
            submarketsWithFleetMember.put(submarket, getFleetMembers(submarket, ship));
        }
    }
}
