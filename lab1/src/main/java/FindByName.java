public class FindByName implements IPersonFinder<String> {
    @Override
    public <String>Person[] find(String key, Person[] personList) {
        int size = 0;
        Person[] found = new Person[size];
        for (Person p : personList) {
            if (p.getName().equals(key)) {
                Person[] tmp = new Person[++size];
                for (int i = 0; i < size - 1; i++) {
                    tmp[i] = found[i];
                    tmp[i + 1] = p;
                }
                tmp[size - 1] = p;
                found = tmp;
            }
        }
        return found;
    }

}
