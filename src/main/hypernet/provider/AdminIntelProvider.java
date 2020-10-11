package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.HypernetIntel;
import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.MarketFilter;
import hypernet.filter.MarketHasAdministrator;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;
import hypernet.subject.AdminSubject;

public class AdminIntelProvider implements IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        IntelList intels = new IntelList();
        List<MarketAPI> markets = MarketHelper.getMarkets();
        MarketFilter filter = new MarketHasAdministrator();
        CollectionHelper.reduce(markets, filter);
        for (MarketAPI market : markets) {
            AdminSubject subject = new AdminSubject(market);
            intels.add(new HypernetIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    @Override
    public String getDescription() {
        AdminSubject subject = new AdminSubject(null);
        return subject.getIntelTitle();
    }
}
