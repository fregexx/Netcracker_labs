package comparator;

@FunctionalInterface
public interface IComparator<T> {
    int compare(T item1, T item2);
}

