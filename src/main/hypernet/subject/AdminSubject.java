package hypernet.subject;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.filter.MarketFilter;
import hypernet.filter.MarketHasAdministrator;
import hypernet.filter.PersonAdministrator;
import hypernet.filter.PersonFilter;

public class AdminSubject extends PersonSubject {

    public AdminSubject(MarketAPI m) {
        super("freelance administrator", m);
    }

    @Override
    public boolean canAcquire() {
        return true;
    }

    @Override
    public boolean isAvailable() {
        MarketFilter filter = new MarketHasAdministrator();
        return filter.accept(market);
    }

    @Override
    protected List<PersonFilter> getFilters() {
        return Arrays.<PersonFilter>asList(new PersonAdministrator());
    }
}
