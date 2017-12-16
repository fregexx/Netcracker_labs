package sorters.impls;

import comparator.IPersonComparator;
import models.Person;
import sorters.IPersonListSorter;

public class InsertionSorter implements IPersonListSorter {

    @Override
    public Person[] sort(Person[] personList, int size, IPersonComparator comparator) {
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(personList[j - 1], personList[j]) > 0) {
                    Person tmp = personList[j];
                    personList[j] = personList[j - 1];
                    personList[j - 1] = tmp;
                }
            }
        }
        return personList;
    }
}
