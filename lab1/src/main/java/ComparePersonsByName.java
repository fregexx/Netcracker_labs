public class ComparePersonsByName implements IPersonComparator {

    @Override
    public boolean compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName()) > 0;
    }
}
