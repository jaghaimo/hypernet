
package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.SubmarketFilter;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;

public abstract class SubmarketProvider extends IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        SubmarketFilter filter = getFilter();
        CollectionHelper.reduce(submarkets, filter);
        return super.provide(MarketHelper.extractMarkets(submarkets));
    }

    protected abstract SubmarketFilter getFilter();
}
