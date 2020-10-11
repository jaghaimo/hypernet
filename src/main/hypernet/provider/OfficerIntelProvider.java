package hypernet.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.HypernetIntel;
import hypernet.IntelList;
import hypernet.IntelProvider;
import hypernet.filter.FilterManager;
import hypernet.filter.MarketFilter;
import hypernet.filter.MarketHasOfficer;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;
import hypernet.subject.OfficerSubject;

public class OfficerIntelProvider implements IntelProvider {

    private String personality;

    public OfficerIntelProvider(String p) {
        personality = p;
    }

    @Override
    public IntelList provide(FilterManager filterManager) {
        IntelList intels = new IntelList();
        List<MarketAPI> markets = MarketHelper.getMarkets();
        MarketFilter filter = new MarketHasOfficer(personality);
        CollectionHelper.reduce(markets, filter);
        for (MarketAPI market : markets) {
            OfficerSubject subject = new OfficerSubject(personality, market);
            intels.add(new HypernetIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    @Override
    public String getDescription() {
        OfficerSubject subject = new OfficerSubject(personality, null);
        return subject.getIntelTitle();
    }
}
