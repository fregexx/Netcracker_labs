package comparator.impls;

import comparator.IPersonComparator;
import models.Person;

public class ComparePersonsById implements IPersonComparator {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getId() > p2.getId() ? 1 : (p1.getId() == p2.getId() ? 0 : -1);
    }
}
