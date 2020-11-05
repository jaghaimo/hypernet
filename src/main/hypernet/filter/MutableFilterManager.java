package hypernet.filter;

import java.util.ArrayList;
import java.util.List;

import hypernet.DialogOption;

public class MutableFilterManager implements FilterManager {

    private DialogOption cargoType = DialogOption.CARGO_TYPE_WEAPON;
    private DialogOption cargoWeaponSize = DialogOption.WEAPON_SIZE_ANY;
    private DialogOption cargoWeaponType = DialogOption.WEAPON_TYPE_ANY;
    private DialogOption cargoWingType = DialogOption.WING_TYPE_ANY;

    private DialogOption fleetShipSize = DialogOption.SHIP_SIZE_FRIGATE;
    private DialogOption fleetShipDamaged = DialogOption.SHIP_DAMAGED_NO;
    private DialogOption fleetShipCarrier = DialogOption.SHIP_CARRIER_NO;
    private DialogOption fleetShipCivilian = DialogOption.SHIP_CIVILIAN_NO;

    private DialogOption staffType = DialogOption.STAFF_TYPE_OFFICER;
    private DialogOption staffOfficer = DialogOption.OFFICER_STEADY;

    public List<CargoStackFilter> listCargoFilters() {
        List<CargoStackFilter> filters = new ArrayList<CargoStackFilter>();
        filters.add(new CargoStackType(cargoType));

        if (cargoType == DialogOption.CARGO_TYPE_WEAPON) {
            filters.add(new CargoStackWeaponSize(cargoWeaponSize));
            filters.add(new CargoStackWeaponType(cargoWeaponType));
        } else if (cargoType == DialogOption.CARGO_TYPE_FIGHTER) {
            filters.add(new CargoStackWingType(cargoWingType));
        }

        return filters;
    }

    public List<FleetMemberFilter> listFleetFilters() {
        List<FleetMemberFilter> filters = new ArrayList<FleetMemberFilter>();
        filters.add(new FleetMemberSize(fleetShipSize));
        filters.add(new FleetMemberDamaged(fleetShipDamaged));
        filters.add(new FleetMemberCarrier(fleetShipCarrier));
        filters.add(new FleetMemberCivilian(fleetShipCivilian));

        return filters;
    }

    public List<MarketFilter> listStaffFilters() {
        List<MarketFilter> filters = new ArrayList<MarketFilter>();

        if (staffType == DialogOption.STAFF_TYPE_ADMIN) {
            filters.add(new MarketHasAdministrator());
        } else {
            filters.add(new MarketHasOfficer(extractStaffOfficerPersonality()));
        }

        return filters;
    }

    public DialogOption getCargoType() {
        return cargoType;
    }

    public void setCargoType(DialogOption option) {
        if (option.isType("CARGO_TYPE")) {
            cargoType = option.getNext();
        }
    }

    public DialogOption getCargoWeaponSize() {
        return cargoWeaponSize;
    }

    public void setCargoWeaponSize(DialogOption option) {
        if (option.isType("WEAPON_SIZE")) {
            cargoWeaponSize = option.getNext();
        }
    }

    public DialogOption getCargoWeaponType() {
        return cargoWeaponType;
    }

    public void setCargoWeaponType(DialogOption option) {
        if (option.isType("WEAPON_TYPE")) {
            cargoWeaponType = option.getNext();
        }
    }

    public DialogOption getCargoWingType() {
        return cargoWingType;
    }

    public void setCargoWingType(DialogOption option) {
        if (option.isType("WING_TYPE")) {
            cargoWingType = option.getNext();
        }
    }

    public DialogOption getFleetShipSize() {
        return fleetShipSize;
    }

    public void setFleetShipSize(DialogOption option) {
        if (option.isType("SHIP_SIZE")) {
            fleetShipSize = option.getNext();
        }
    }

    public DialogOption getFleetShipDamaged() {
        return fleetShipDamaged;
    }

    public void setFleetShipDamaged(DialogOption option) {
        if (option.isType("SHIP_DAMAGED")) {
            fleetShipDamaged = option.getNext();
        }
    }

    public DialogOption getFleetShipCarrier() {
        return fleetShipCarrier;
    }

    public void setFleetShipCarrier(DialogOption option) {
        if (option.isType("SHIP_CARRIER")) {
            fleetShipCarrier = option.getNext();
        }
    }

    public DialogOption getFleetShipCivilian() {
        return fleetShipCivilian;
    }

    public void setFleetShipCivilian(DialogOption option) {
        if (option.isType("SHIP_CIVILIAN")) {
            fleetShipCivilian = option.getNext();
        }
    }

    public DialogOption getStaffType() {
        return staffType;
    }

    public void setStaffType(DialogOption option) {
        if (option.isType("STAFF_TYPE")) {
            staffType = option.getNext();
        }
    }

    public DialogOption getStaffOfficer() {
        return staffOfficer;
    }

    public void setStaffOfficer(DialogOption option) {
        if (option.isType("OFFICER")) {
            staffOfficer = option.getNext();
        }
    }

    public String extractStaffOfficerPersonality() {
        return staffOfficer.name().substring(8).toLowerCase();
    }
}
