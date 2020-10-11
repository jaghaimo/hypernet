package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import hypernet.HypernetIntel;
import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketHasCargoStack;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;
import hypernet.subject.CargoSubject;

public class CargoIntelProvider implements IntelProvider {

    private CargoStackAPI cargoStack;

    public CargoIntelProvider(CargoStackAPI c) {
        cargoStack = c;
    }

    @Override
    public IntelList provide(FilterManager filterManager) {
        IntelList intels = new IntelList();
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        SubmarketFilter filter = new SubmarketHasCargoStack(cargoStack);
        CollectionHelper.reduce(submarkets, filter);
        for (SubmarketAPI submarket : submarkets) {
            MarketAPI market = submarket.getMarket();
            CargoSubject subject = new CargoSubject(cargoStack, market);
            intels.add(new HypernetIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    @Override
    public String getDescription() {
        CargoSubject subject = new CargoSubject(cargoStack, null);
        return subject.getIntelTitle();
    }
}
