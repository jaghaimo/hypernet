package hypernet.subject;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.filter.PersonAdministrator;
import hypernet.filter.PersonFilter;
import hypernet.helper.MarketHelper;

public class AdminSubject extends PersonSubject {

    public AdminSubject(MarketAPI m) {
        super("freelance administrator", m);
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market);
    }

    @Override
    protected List<PersonFilter> getFilters() {
        return Arrays.<PersonFilter>asList(new PersonAdministrator());
    }
}
