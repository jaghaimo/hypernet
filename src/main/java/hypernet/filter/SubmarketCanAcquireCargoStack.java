package hypernet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

public class SubmarketCanAcquireCargoStack implements SubmarketFilter {

    private CargoStackAPI cargoStack;

    public SubmarketCanAcquireCargoStack(CargoStackAPI cargoStack) {
        this.cargoStack = cargoStack;
    }

    public boolean accept(SubmarketAPI submarket) {
        return !submarket.getPlugin().isIllegalOnSubmarket(cargoStack, TransferAction.PLAYER_BUY);
    }
}
