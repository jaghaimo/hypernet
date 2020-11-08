package hypernet.subject;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.filter.PersonFilter;
import hypernet.filter.PersonOfficer;
import hypernet.filter.PersonPersonality;
import hypernet.helper.MarketHelper;

public class OfficerSubject extends PersonSubject {

    private String personality;

    public OfficerSubject(String p, MarketAPI m) {
        super(p + " officer", m);
        personality = p;
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market, personality);
    }

    @Override
    protected List<PersonFilter> getFilters() {
        return Arrays.asList(new PersonOfficer(), new PersonPersonality(personality));
    }
}
