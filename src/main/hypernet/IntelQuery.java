package hypernet;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;

import hypernet.filter.FilterManager;
import hypernet.filter.ImmutableFilterManager;

public class IntelQuery {

    String createdDate;
    long updatedDate;
    boolean isEnabled;
    FilterManager filterManager;
    IntelList hypernetIntels;
    IntelProvider intelProvider;

    public IntelQuery(IntelProvider ip, FilterManager fm) {
        CampaignClockAPI clock = getCurrentClock();
        createdDate = clock.getDateString();
        updatedDate = clock.getTimestamp();
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
        String off = isEnabled() ? "" : " (disabled)";
        String stale = isStale() ? " (stale)" : "";
        return intelProvider.getDescription() + off + stale;
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

    public boolean isActive() {
        return isEnabled() && !isStale();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isStale() {
        CampaignClockAPI current = getCurrentClock();
        CampaignClockAPI lastUpdate = current.createClock(updatedDate);
        if (current.getCycle() != lastUpdate.getCycle()) {
            return true;
        }
        if (current.getMonth() != lastUpdate.getMonth()) {
            return true;
        }
        return false;
    }

    public void refresh() {
        hypernetIntels.removeIntel();
        hypernetIntels = intelProvider.provide(filterManager);
        hypernetIntels.addIntel(isEnabled);
        updatedDate = getCurrentClock().getTimestamp();
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }

    private CampaignClockAPI getCurrentClock() {
        return Global.getSector().getClock();
    }
}
