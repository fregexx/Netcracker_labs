public interface IPersonListSorter {
    Person[] sort(Person[] personList, int size, IPersonComparator comparator);
}
