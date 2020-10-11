package hypernet;

import hypernet.filter.FilterManager;

public interface IntelProvider {

    public String getDescription();

    public IntelList provide(FilterManager filterManager);
}
