package hypernet.subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.filter.SubmarketCanAcquireFleetMember;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasFleetMember;
import hypernet.filter.SubmarketIsAccessible;
import hypernet.helper.CollectionHelper;
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
        List<SubmarketFilter> filters = Arrays.asList(new SubmarketHasFleetMember(ship),
                new SubmarketCanAcquireFleetMember(ship), new SubmarketIsAccessible());
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets(market);
        CollectionHelper.reduce(submarkets, filters);
        return !submarkets.isEmpty();
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
        SubmarketFilter filter = new SubmarketHasFleetMember(ship);
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets(market);
        CollectionHelper.reduce(submarkets, filter);
        return !submarkets.isEmpty();
    }

    @Override
    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        super.addSubmarket(info, submarket);
        info.showShips(submarketsWithFleetMember.get(submarket), 1, false, 1f);
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
