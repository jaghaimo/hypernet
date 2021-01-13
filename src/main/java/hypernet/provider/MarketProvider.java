package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.MarketFilter;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;

public abstract class MarketProvider extends IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        List<MarketAPI> markets = MarketHelper.getMarkets();
        MarketFilter filter = getFilter();
        CollectionHelper.reduce(markets, filter);
        return super.provide(markets);
    }

    protected abstract MarketFilter getFilter();
}
