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

    private DialogOption staffType = DialogOption.STAFF_OFFICER;
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

        if (staffType == DialogOption.STAFF_ADMIN) {
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
        if (option == DialogOption.CARGO_TYPE_WEAPON) {
            cargoType = DialogOption.CARGO_TYPE_FIGHTER;
        } else if (option == DialogOption.CARGO_TYPE_FIGHTER) {
            cargoType = DialogOption.CARGO_TYPE_MODSPEC;
        } else if (option == DialogOption.CARGO_TYPE_MODSPEC) {
            cargoType = DialogOption.CARGO_TYPE_BLUEPRINT;
        } else if (option == DialogOption.CARGO_TYPE_BLUEPRINT) {
            cargoType = DialogOption.CARGO_TYPE_WEAPON;
        }
    }

    public DialogOption getCargoWeaponSize() {
        return cargoWeaponSize;
    }

    public void setCargoWeaponSize(DialogOption option) {
        if (option == DialogOption.WEAPON_SIZE_ANY) {
            cargoWeaponSize = DialogOption.WEAPON_SIZE_SMALL;
        } else if (option == DialogOption.WEAPON_SIZE_SMALL) {
            cargoWeaponSize = DialogOption.WEAPON_SIZE_MEDIUM;
        } else if (option == DialogOption.WEAPON_SIZE_MEDIUM) {
            cargoWeaponSize = DialogOption.WEAPON_SIZE_LARGE;
        } else if (option == DialogOption.WEAPON_SIZE_LARGE) {
            cargoWeaponSize = DialogOption.WEAPON_SIZE_ANY;
        }
    }

    public DialogOption getCargoWeaponType() {
        return cargoWeaponType;
    }

    public void setCargoWeaponType(DialogOption option) {
        if (option == DialogOption.WEAPON_TYPE_ANY) {
            cargoWeaponType = DialogOption.WEAPON_TYPE_BALLISTIC;
        } else if (option == DialogOption.WEAPON_TYPE_BALLISTIC) {
            cargoWeaponType = DialogOption.WEAPON_TYPE_ENERGY;
        } else if (option == DialogOption.WEAPON_TYPE_ENERGY) {
            cargoWeaponType = DialogOption.WEAPON_TYPE_MISSILE;
        } else if (option == DialogOption.WEAPON_TYPE_MISSILE) {
            cargoWeaponType = DialogOption.WEAPON_TYPE_ANY;
        }
    }

    public DialogOption getCargoWingType() {
        return cargoWingType;
    }

    public void setCargoWingType(DialogOption option) {
        if (option == DialogOption.WING_TYPE_ANY) {
            cargoWingType = DialogOption.WING_TYPE_INTERCEPTOR;
        } else if (option == DialogOption.WING_TYPE_INTERCEPTOR) {
            cargoWingType = DialogOption.WING_TYPE_FIGHTER;
        } else if (option == DialogOption.WING_TYPE_FIGHTER) {
            cargoWingType = DialogOption.WING_TYPE_BOMBER;
        } else if (option == DialogOption.WING_TYPE_BOMBER) {
            cargoWingType = DialogOption.WING_TYPE_ANY;
        }
    }

    public DialogOption getFleetShipSize() {
        return fleetShipSize;
    }

    public void setFleetShipSize(DialogOption option) {
        if (option == DialogOption.SHIP_SIZE_CAPITAL) {
            fleetShipSize = DialogOption.SHIP_SIZE_FRIGATE;
        } else if (option == DialogOption.SHIP_SIZE_FRIGATE) {
            fleetShipSize = DialogOption.SHIP_SIZE_DESTROYER;
        } else if (option == DialogOption.SHIP_SIZE_DESTROYER) {
            fleetShipSize = DialogOption.SHIP_SIZE_CRUISER;
        } else if (option == DialogOption.SHIP_SIZE_CRUISER) {
            fleetShipSize = DialogOption.SHIP_SIZE_CAPITAL;
        }
    }

    public DialogOption getFleetShipDamaged() {
        return fleetShipDamaged;
    }

    public void setFleetShipDamaged(DialogOption option) {
        if (option == DialogOption.SHIP_DAMAGED_NO) {
            fleetShipDamaged = DialogOption.SHIP_DAMAGED_YES;
        } else if (option == DialogOption.SHIP_DAMAGED_YES) {
            fleetShipDamaged = DialogOption.SHIP_DAMAGED_ONLY;
        } else if (option == DialogOption.SHIP_DAMAGED_ONLY) {
            fleetShipDamaged = DialogOption.SHIP_DAMAGED_NO;
        }
    }

    public DialogOption getFleetShipCarrier() {
        return fleetShipCarrier;
    }

    public void setFleetShipCarrier(DialogOption option) {
        if (option == DialogOption.SHIP_CARRIER_NO) {
            fleetShipCarrier = DialogOption.SHIP_CARRIER_YES;
        } else if (option == DialogOption.SHIP_CARRIER_YES) {
            fleetShipCarrier = DialogOption.SHIP_CARRIER_ONLY;
        } else if (option == DialogOption.SHIP_CARRIER_ONLY) {
            fleetShipCarrier = DialogOption.SHIP_CARRIER_NO;
        }
    }

    public DialogOption getFleetShipCivilian() {
        return fleetShipCivilian;
    }

    public void setFleetShipCivilian(DialogOption option) {
        if (option == DialogOption.SHIP_CIVILIAN_NO) {
            fleetShipCivilian = DialogOption.SHIP_CIVILIAN_YES;
        } else if (option == DialogOption.SHIP_CIVILIAN_YES) {
            fleetShipCivilian = DialogOption.SHIP_CIVILIAN_ONLY;
        } else if (option == DialogOption.SHIP_CIVILIAN_ONLY) {
            fleetShipCivilian = DialogOption.SHIP_CIVILIAN_NO;
        }
    }

    public DialogOption getStaffType() {
        return staffType;
    }

    public void setStaffType(DialogOption option) {
        if (option == DialogOption.STAFF_OFFICER) {
            staffType = DialogOption.STAFF_ADMIN;
        } else if (option == DialogOption.STAFF_ADMIN) {
            staffType = DialogOption.STAFF_OFFICER;
        }
    }

    public DialogOption getStaffOfficer() {
        return staffOfficer;
    }

    public void setStaffOfficer(DialogOption option) {
        if (option == DialogOption.OFFICER_TIMID) {
            staffOfficer = DialogOption.OFFICER_CAUTIOUS;
        } else if (option == DialogOption.OFFICER_CAUTIOUS) {
            staffOfficer = DialogOption.OFFICER_STEADY;
        } else if (option == DialogOption.OFFICER_STEADY) {
            staffOfficer = DialogOption.OFFICER_AGGRESSIVE;
        } else if (option == DialogOption.OFFICER_AGGRESSIVE) {
            staffOfficer = DialogOption.OFFICER_RECKLESS;
        } else if (option == DialogOption.OFFICER_RECKLESS) {
            staffOfficer = DialogOption.OFFICER_TIMID;
        }
    }

    public String extractStaffOfficerPersonality() {
        return staffOfficer.name().substring(8).toLowerCase();
    }
}
