package hypernet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

public class SubmarketHasCargoStack implements SubmarketFilter {

    private CargoStackAPI cargoStack;

    public SubmarketHasCargoStack(CargoStackAPI cs) {
        cargoStack = cs;
    }

    public boolean accept(SubmarketAPI submarket) {
        for (CargoStackAPI c : submarket.getCargo().getStacksCopy()) {
            if (cargoStack.getDisplayName().equals(c.getDisplayName())) {
                return !submarket.getPlugin().isIllegalOnSubmarket(c, TransferAction.PLAYER_BUY);
            }
        }

        return false;
    }
}
