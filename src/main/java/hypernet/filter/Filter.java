package hypernet.filter;

public interface Filter<T> {

    public boolean accept(T object);
}
