public class BubbleSort implements IPersonListSorter{

    @Override
    public Person[] sort(Person[] personList, IPersonComparator comparator) {
        for (int i = personList.length - 1; i > 0; i--) {
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
