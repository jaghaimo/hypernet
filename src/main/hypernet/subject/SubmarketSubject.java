package hypernet.subject;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.IntelSubject;
import hypernet.filter.CargoStacksHasStack;
import hypernet.filter.FleetMembersHasMember;
import hypernet.filter.SubmarketFilter;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;

public abstract class SubmarketSubject extends IntelSubject {

    public SubmarketSubject(String e, MarketAPI m) {
        super(e, m);
    }

    protected void addBasicInfo(TooltipMakerAPI info) {
        int submarketCount = getSubmarketCount();
        String isOrAreSubmarkets = submarketCount == 1 ? "is" : "are";
        String submarketOrSubmarkets = submarketCount == 1 ? "" : "s";

        int entityCount = getEntityCount();
        String entityOrEntities = entityCount == 1 ? "" : "s";

        String basicInfo = String.format("There %s %d submarket%s with a total of %d %s%s on %s. ", isOrAreSubmarkets,
                submarketCount, submarketOrSubmarkets, entityCount, entity, entityOrEntities, market.getName());
        super.addBasicInfo(info, basicInfo);
    }

    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        FactionAPI faction = submarket.getFaction();
        info.addPara("", 0f);
        info.addPara(submarket.getNameOneLine(), faction.getBaseUIColor(), 10f);
    }

    protected CargoStackAPI getCargoStack(SubmarketAPI submarket, CargoStackAPI cargoStack) {
        List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
        CollectionHelper.reduce(cargoStacks, new CargoStacksHasStack(cargoStack));
        if (cargoStacks.isEmpty()) {
            return cargoStack;
        }
        return cargoStacks.get(0);
    }

    protected List<FleetMemberAPI> getFleetMembers(SubmarketAPI submarket, FleetMemberAPI fleetMember) {
        List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
        CollectionHelper.reduce(fleetMembers, new FleetMembersHasMember(fleetMember));
        return fleetMembers;
    }

    protected List<SubmarketAPI> getSubmarkets() {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets(market);
        CollectionHelper.reduce(submarkets, getFilter());
        return submarkets;
    }

    protected abstract int getEntityCount();

    protected abstract SubmarketFilter getFilter();

    protected abstract int getSubmarketCount();
}
