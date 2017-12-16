package sorters;

import comparator.IPersonComparator;
import models.Person;
import repositories.PersonRepository;

public interface IPersonListSorter {
    Person[] sort(Person[] personList, int size, IPersonComparator comparator);
}
