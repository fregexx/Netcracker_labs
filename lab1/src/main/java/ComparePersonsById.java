public class ComparePersonsById  implements IPersonComparator {
    @Override
    public boolean compare(Person p1, Person p2) {
        return p1.getId()>p2.getId();
    }
}
