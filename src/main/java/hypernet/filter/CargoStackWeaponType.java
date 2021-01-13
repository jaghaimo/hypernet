package hypernet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;

import hypernet.DialogOption;

public class CargoStackWeaponType implements CargoStackFilter {

    private DialogOption option;

    public CargoStackWeaponType(DialogOption o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isWeaponStack()) {
            return false;
        }

        WeaponType weaponType = c.getWeaponSpecIfWeapon().getType();

        switch (option) {
            case WEAPON_TYPE_BALLISTIC:
                return weaponType.equals(WeaponType.BALLISTIC);

            case WEAPON_TYPE_ENERGY:
                return weaponType.equals(WeaponType.ENERGY);

            case WEAPON_TYPE_MISSILE:
                return weaponType.equals(WeaponType.MISSILE);

            default:
                return true;
        }
    }
}
