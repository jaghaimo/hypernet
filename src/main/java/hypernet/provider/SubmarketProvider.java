
package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.SubmarketFilter;
import hypernet.filter.SubmarketIsNotStorage;
import hypernet.helper.CollectionHelper;
import hypernet.helper.ConfigHelper;
import hypernet.helper.MarketHelper;

public abstract class SubmarketProvider extends IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        SubmarketFilter filter = getFilter();
        CollectionHelper.reduce(submarkets, filter);
        if (!ConfigHelper.includePlayerStorage()) {
            SubmarketFilter notStorage = new SubmarketIsNotStorage();
            CollectionHelper.reduce(submarkets, notStorage);
        }
        return super.provide(MarketHelper.extractMarkets(submarkets));
    }

    protected abstract SubmarketFilter getFilter();
}
