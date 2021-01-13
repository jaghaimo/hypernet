package hypernet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WingRole;

import hypernet.DialogOption;

public class CargoStackWingType implements CargoStackFilter {

    private DialogOption option;

    public CargoStackWingType(DialogOption o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isFighterWingStack()) {
            return false;
        }

        WingRole wingRole = c.getFighterWingSpecIfWing().getRole();

        switch (option) {
            case WING_TYPE_BOMBER:
                return wingRole.equals(WingRole.BOMBER);

            case WING_TYPE_FIGHTER:
                return wingRole.equals(WingRole.FIGHTER);

            case WING_TYPE_INTERCEPTOR:
                return wingRole.equals(WingRole.INTERCEPTOR);

            default:
                return true;
        }
    }
}
