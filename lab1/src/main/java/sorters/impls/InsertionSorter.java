package sorters.impls;

import comparator.IComparator;
import sorters.ISorter;

public class InsertionSorter<T> implements ISorter<T> {
    @Override
    public T[] sort(T[] list, int size, IComparator<T> comparator) {
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(list[j - 1], list[j]) > 0) {
                    T tmp = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = tmp;
                }
            }
        }
        return list;
    }
}
