public class InsertionSort implements IPersonListSorter {

    @Override
    public Person[] sort(Person[] personList, int size, IPersonComparator comparator) {
        int temp;
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(personList[j - 1], personList[j])) {
                    Person tmp = personList[j];
                    personList[j] = personList[j - 1];
                    personList[j - 1] = tmp;
                }
            }
        }
        return personList;
    }
}
