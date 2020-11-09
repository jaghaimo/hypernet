package hypernet.subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.filter.SubmarketCanAcquireCargoStack;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasCargoStack;
import hypernet.filter.SubmarketIsAccessible;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;

public class CargoSubject extends SubmarketSubject {

    private CargoStackAPI cargoStack;
    private Map<SubmarketAPI, CargoStackAPI> submarketsWithCargoStack;

    public CargoSubject(CargoStackAPI c, MarketAPI m) {
        super(c.getDisplayName(), m);
        cargoStack = c;
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        populate();
        addHeader(info, width);
        addBasicInfo(info);
        for (SubmarketAPI submarket : submarketsWithCargoStack.keySet()) {
            addSubmarket(info, submarket);
        }
    }

    @Override
    public boolean canAcquire() {
        List<SubmarketFilter> filters = Arrays.asList(new SubmarketHasCargoStack(cargoStack),
                new SubmarketIsAccessible(), new SubmarketCanAcquireCargoStack(cargoStack));
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets(market);
        CollectionHelper.reduce(submarkets, filters);
        return !submarkets.isEmpty();
    }

    @Override
    public String getIcon() {
        if (cargoStack.isWeaponStack()) {
            return cargoStack.getWeaponSpecIfWeapon().getTurretSpriteName();
        }

        if (cargoStack.isFighterWingStack()) {
            return cargoStack.getFighterWingSpecIfWing().getId();
        }

        if (cargoStack.isSpecialStack()) {
            return cargoStack.getSpecialItemSpecIfSpecial().getIconName();
        }

        return super.getIcon();
    }

    @Override
    public boolean isAvailable() {
        SubmarketFilter filter = new SubmarketHasCargoStack(cargoStack);
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets(market);
        CollectionHelper.reduce(submarkets, filter);
        return !submarkets.isEmpty();
    }

    @Override
    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        super.addSubmarket(info, submarket);
        CargoAPI cargo = Global.getFactory().createCargo(false);
        cargo.addFromStack(submarketsWithCargoStack.get(submarket));
        info.showCargo(cargo, 1, false, 1f);
    }

    @Override
    protected int getEntityCount() {
        float count = 0;
        for (CargoStackAPI stack : submarketsWithCargoStack.values()) {
            count += stack.getSize();
        }
        return (int) count;
    }

    @Override
    protected int getSubmarketCount() {
        return submarketsWithCargoStack.size();
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new SubmarketHasCargoStack(cargoStack);
    }

    private void populate() {
        submarketsWithCargoStack = new HashMap<>();
        for (SubmarketAPI submarket : getSubmarkets()) {
            submarketsWithCargoStack.put(submarket, getCargoStack(submarket, cargoStack));
        }
    }
}
