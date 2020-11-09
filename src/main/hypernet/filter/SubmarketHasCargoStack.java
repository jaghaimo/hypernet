package hypernet.filter;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import hypernet.helper.CollectionHelper;

public class SubmarketHasCargoStack implements SubmarketFilter {

    private CargoStacksHasStack filter;

    public SubmarketHasCargoStack(CargoStackAPI cargoStack) {
        filter = new CargoStacksHasStack(cargoStack);
    }

    public boolean accept(SubmarketAPI submarket) {
        List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
        CollectionHelper.reduce(cargoStacks, filter);
        return !cargoStacks.isEmpty();
    }
}
