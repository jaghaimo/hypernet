package hypernet.subject;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelSubject;
import hypernet.helper.MarketHelper;

public class AdminSubject extends IntelSubject {

    public AdminSubject(MarketAPI m) {
        super("administrator", m);
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market);
    }
}
