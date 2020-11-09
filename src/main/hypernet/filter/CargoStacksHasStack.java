package hypernet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStacksHasStack implements Filter<CargoStackAPI> {

    private CargoStackAPI cargoStack;

    public CargoStacksHasStack(CargoStackAPI c) {
        cargoStack = c;
    }

    public boolean accept(CargoStackAPI c) {
        return cargoStack.getDisplayName().equals(c.getDisplayName());
    }
}
