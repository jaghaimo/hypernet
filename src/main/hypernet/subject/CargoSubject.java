package hypernet.subject;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import hypernet.IntelSubject;
import hypernet.helper.MarketHelper;

public class CargoSubject extends IntelSubject {

    private CargoStackAPI cargoStack;

    public CargoSubject(CargoStackAPI c, MarketAPI m) {
        super(c.getDisplayName(), m);
        cargoStack = c;
    }

    @Override
    public boolean canAcquire() {
        return MarketHelper.canAcquire(market, cargoStack);
    }

    @Override
    public String getIcon() {
        if (cargoStack.isWeaponStack()) {
            return cargoStack.getWeaponSpecIfWeapon().getTurretSpriteName();
        }

        if (cargoStack.isFighterWingStack()) {
            return cargoStack.getFighterWingSpecIfWing().getId();
        }

        if (cargoStack.isSpecialStack()) {
            return cargoStack.getSpecialItemSpecIfSpecial().getIconName();
        }

        return super.getIcon();
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market, cargoStack);
    }
}
