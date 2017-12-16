package comparator.impls;

import comparator.IPersonComparator;
import models.Person;

public class ComparePersonsByName implements IPersonComparator {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
