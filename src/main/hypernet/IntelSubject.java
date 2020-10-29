package hypernet;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;

public abstract class IntelSubject {

    protected String entity;
    protected MarketAPI market;

    public IntelSubject(String e, MarketAPI m) {
        entity = e;
        market = m;
    }

    public boolean canAcquire() {
        return market != null;
    }

    public String getIcon() {
        return market.getFaction().getCrest();
    }

    public String getIntelTitle() {
        return Misc.ucFirst(entity);
    }

    public boolean isAvailable() {
        return market != null;
    }
}
