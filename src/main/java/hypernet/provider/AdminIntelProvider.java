package hypernet.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelSubject;
import hypernet.filter.MarketFilter;
import hypernet.filter.MarketHasAdministrator;
import hypernet.subject.AdminSubject;

public class AdminIntelProvider extends MarketProvider {

    @Override
    protected MarketFilter getFilter() {
        return new MarketHasAdministrator();
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new AdminSubject(market);
    }
}
