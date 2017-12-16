package sorters.impls;

import models.Person;
import comparator.IPersonComparator;
import repositories.PersonRepository;
import sorters.IPersonListSorter;

import java.util.Arrays;

public class BubbleSorter implements IPersonListSorter {

    @Override
    public Person[] sort(Person[] personList, int size, IPersonComparator comparator) {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(personList[j], personList[j + 1]) > 0) {
                    Person tmp = personList[j];
                    personList[j] = personList[j + 1];
                    personList[j + 1] = tmp;
                }
            }
        }
        return personList;
    }
}

