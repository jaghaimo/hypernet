package hypernet.subject;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelSubject;
import hypernet.helper.MarketHelper;

public class OfficerSubject extends IntelSubject {

    private String personality;

    public OfficerSubject(String p, MarketAPI m) {
        super(p + " officer", m);
        personality = p;
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market, personality);
    }
}
