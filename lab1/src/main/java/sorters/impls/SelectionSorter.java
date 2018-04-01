package sorters.impls;

import comparator.IComparator;
import models.Person;
import sorters.ISorter;

public class SelectionSorter<T> implements ISorter<T> {

    @Override
    public T[] sort(T[] list, int size, IComparator<T> comparator) {
        for (int i = 0; i < size; i++) {
            T min = list[i];
            int min_i = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(min, list[j]) > 0) {
                    min = list[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                T tmp = list[i];
                list[i] = list[min_i];
                list[min_i] = tmp;
            }
        }
        return list;
    }
}
