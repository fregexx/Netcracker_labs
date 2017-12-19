package sorters;

import comparator.IComparator;

@FunctionalInterface
public interface ISorter<T> {
    T[] sort(T[] list, int size, IComparator<T> comparator);
}
