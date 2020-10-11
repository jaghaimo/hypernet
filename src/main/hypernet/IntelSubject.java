package hypernet;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;

public abstract class IntelSubject {

    protected String entity;
    protected MarketAPI market;

    public IntelSubject(String e, MarketAPI m) {
        entity = e;
        market = m;
    }

    public String getIcon() {
        return market.getFaction().getCrest();
    }

    public String getIntelTitle() {
        return Misc.ucFirst(entity);
    }

    public String getIntelInfo() {
        return Misc.ucFirst(entity) + " can be found on " + market.getName() + getStarSystemName(" in ");
    }

    public String getKey() {
        return entity + market.getName() + getStarSystemName("");
    }

    public String getStarSystemName(String prefix) {
        StarSystemAPI starSystem = market.getStarSystem();

        if (starSystem != null) {
            return prefix + starSystem.getName();
        }

        return "";
    }

    public boolean isAvailable() {
        return market == null;
    }
}
