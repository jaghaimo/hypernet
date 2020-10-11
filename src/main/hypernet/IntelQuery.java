package hypernet;

import com.fs.starfarer.api.Global;

import hypernet.filter.FilterManager;
import hypernet.filter.ImmutableFilterManager;

public class IntelQuery {

    String createdDate;
    boolean isEnabled;
    FilterManager filterManager;
    IntelList hypernetIntels;
    IntelProvider intelProvider;

    public IntelQuery(IntelProvider ip, FilterManager fm) {
        createdDate = Global.getSector().getClock().getDateString();
        isEnabled = true;
        filterManager = new ImmutableFilterManager(fm);
        hypernetIntels = ip.provide(filterManager);
        hypernetIntels.addIntel(isEnabled);
        intelProvider = ip;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return intelProvider.getDescription();
    }

    public String getResultCount() {
        int count = hypernetIntels.size();
        String intelOrIntels = count == 1 ? " result" : " results";
        return String.valueOf(count) + intelOrIntels;
    }

    public void disable() {
        isEnabled = false;
        hypernetIntels.removeIntel();
    }

    public void enable() {
        isEnabled = true;
        hypernetIntels.addIntel(isEnabled);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void refresh() {
        hypernetIntels.removeIntel();
        hypernetIntels = intelProvider.provide(filterManager);
        hypernetIntels.addIntel(isEnabled);
    }
}
