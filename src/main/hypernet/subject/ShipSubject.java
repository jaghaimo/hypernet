package hypernet.subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.filter.SubmarketCanAcquireFleetMember;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasFleetMember;
import hypernet.filter.SubmarketIsAccessible;
import hypernet.helper.CollectionHelper;

public class ShipSubject extends SubmarketSubject {

    private FleetMemberAPI ship;
    private Map<SubmarketAPI, List<FleetMemberAPI>> submarketsWithFleetMembers;

    public ShipSubject(FleetMemberAPI s, MarketAPI m) {
        super(s.getHullSpec().getHullNameWithDashClass() + " " + s.getHullSpec().getDesignation(), m);
        ship = s;
    }

    @Override
    public boolean canAcquire() {
        List<SubmarketFilter> filters = Arrays.asList(new SubmarketHasFleetMember(ship),
                new SubmarketCanAcquireFleetMember(ship), new SubmarketIsAccessible());
        List<SubmarketAPI> submarkets = market.getSubmarketsCopy();
        CollectionHelper.reduce(submarkets, filters);
        return !submarkets.isEmpty();
    }

    @Override
    public String getIcon() {
        return ship.getHullSpec().getSpriteName();
    }

    @Override
    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        super.addSubmarket(info, submarket);
        info.showShips(submarketsWithFleetMembers.get(submarket), 1, false, 3f);
    }

    @Override
    protected int getEntityCount() {
        int count = 0;
        for (List<FleetMemberAPI> fleetMembers : submarketsWithFleetMembers.values()) {
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
        return submarketsWithFleetMembers.size();
    }

    @Override
    protected Set<SubmarketAPI> getSubmarkets() {
        return submarketsWithFleetMembers.keySet();
    }

    @Override
    protected void populate() {
        submarketsWithFleetMembers = new HashMap<>();
        for (SubmarketAPI submarket : findSubmarkets()) {
            submarketsWithFleetMembers.put(submarket, getFleetMembers(submarket, ship));
        }
    }
}
