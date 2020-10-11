package hypernet.handler.dialog;

import hypernet.filter.MutableFilterManager;
import hypernet.DialogOption;
import hypernet.DialogPlugin;

public class Staff extends FilterAware {

    public Staff(DialogOption o, DialogOption p) {
        super(o, p);
    }

    @Override
    protected DialogOption run(DialogPlugin plugin) {
        MutableFilterManager filterManager = plugin.getFilterManager();

        if (filterManager.getStaffType().equals(DialogOption.STAFF_ADMIN)) {
            plugin.addText("Querying markets for freelance administrators.");
            plugin.addIntelQuery();
        } else {
            String personality = filterManager.getStaffOfficer().name().substring(8).toLowerCase();
            plugin.addText("Querying markets for " + personality + " officers.");
            plugin.addIntelQuery(personality);
        }

        Menu.forceMenu(plugin);
        return DialogOption.INIT;
    }
}
