package hypernet.provider;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelSubject;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasCargoStack;
import hypernet.subject.CargoSubject;

public class CargoIntelProvider extends SubmarketProvider {

    private CargoStackAPI cargoStack;

    public CargoIntelProvider(CargoStackAPI c) {
        cargoStack = c;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new SubmarketHasCargoStack(cargoStack);
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new CargoSubject(cargoStack, market);
    }
}
