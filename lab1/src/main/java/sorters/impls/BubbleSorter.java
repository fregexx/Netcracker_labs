package sorters.impls;

import comparator.IComparator;
import sorters.ISorter;

public class BubbleSorter<T> implements ISorter<T> {

    @Override
    public T[] sort(T[] list, int size, IComparator<T> comparator) {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    T tmp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = tmp;
                }
            }
        }
        return list;
    }
}
