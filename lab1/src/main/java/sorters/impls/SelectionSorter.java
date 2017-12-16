package sorters.impls;

import comparator.IPersonComparator;
import models.Person;
import sorters.IPersonListSorter;

public class SelectionSorter implements IPersonListSorter {

    @Override
    public Person[] sort(Person[] personList, int size, IPersonComparator comparator) {
        for (int i = 0; i < size; i++) {
            Person min = personList[i];
            int min_i = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(min, personList[j]) > 0) {
                    min = personList[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                Person tmp = personList[i];
                personList[i] = personList[min_i];
                personList[min_i] = tmp;
            }
        }
        return personList;
    }
}
