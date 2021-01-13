package hypernet.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelSubject;
import hypernet.filter.MarketFilter;
import hypernet.filter.MarketHasOfficer;
import hypernet.subject.OfficerSubject;

public class OfficerIntelProvider extends MarketProvider {

    private String personality;

    public OfficerIntelProvider(String p) {
        personality = p;
    }

    @Override
    protected MarketFilter getFilter() {
        return new MarketHasOfficer(personality);
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new OfficerSubject(personality, market);
    }
}
