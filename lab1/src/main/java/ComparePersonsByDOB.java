public class ComparePersonsByDOB implements IPersonComparator {
    @Override
    public boolean compare(Person person1, Person person2) {
        return person1.getDateOfBirth().isAfter(person2.getDateOfBirth());
    }
}
