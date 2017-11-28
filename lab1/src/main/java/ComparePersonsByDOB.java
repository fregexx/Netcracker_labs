public class ComparePersonsByDOB implements IPersonComparator {
    @Override
    public int compare(Person person1, Person person2) {
        if(person1.getDateOfBirth().isEqual(person2.getDateOfBirth())){
            return 0;
        }else{
            if(person1.getDateOfBirth().isAfter(person2.getDateOfBirth())){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
}
