package hypernet.subject;

import java.util.List;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.IntelSubject;
import hypernet.filter.PersonAdministrator;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;

public class AdminSubject extends IntelSubject {

    public AdminSubject(MarketAPI m) {
        super("administrator", m);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        super.createSmallDescription(info, width, height);
        for (CommDirectoryEntryAPI admin : getAdmins()) {
            addAdmin(info, (PersonAPI) admin.getEntryData());
        }
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market);
    }

    private void addAdmin(TooltipMakerAPI info, PersonAPI admin) {
        info.addPara(admin.getNameString(), 10f);
    }

    private List<CommDirectoryEntryAPI> getAdmins() {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionHelper.reduce(people, new PersonAdministrator());
        return people;
    }
}
