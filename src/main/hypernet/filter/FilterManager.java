package hypernet.filter;

import java.util.List;

public interface FilterManager {

    public List<CargoStackFilter> listCargoFilters();

    public List<FleetMemberFilter> listFleetFilters();

    public List<MarketFilter> listStaffFilters();
}
