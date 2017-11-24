import java.util.Arrays;

public class PersonRepository {

    private Person[] personList;
    private int size;

    public PersonRepository() {
        this.personList = null;
        this.size = 0;
    }

    /**
     * Appends the specified Person element to the end of array
     *
     * @param person element to be appended to this array
     */
    void insert(Person person) {
        if (size == 0) {
            personList = new Person[1];
            size++;
            personList[0] = person;
        } else {
            Person[] newList = new Person[size + 1];
            for (int i = 0; i < personList.length; i++) {
                newList[i] = personList[i];
            }
            newList[size] = person;
            size++;
            this.personList = newList;
        }
    }

    /**
     * Removes the Person element with specified id from the array
     *
     * @param id Person id to search for
     */
    void remove(int id) {
        boolean found = false;
        int k = 0;
        for (Person p : personList) {
            if (id == p.getId()) {
                found = true;
                break;
            }
            k++;
        }
        if (found) {
            Person[] newList = new Person[size - 1];
            for (int i = 0; i < k; i++) {
                newList[i] = personList[i];
            }
            for (int i = k + 1; i < size; i++) {
                newList[i - 1] = personList[i];
            }
            size--;
            personList = newList;
        }
    }

    /**
     * Returns an Array of Persons
     *
     * @return Array of Persons
     */
    Person[] getList() {
        return personList;
    }

    /**
     * Prints an Array of Person objects
     * Terminates the line after each Person object
     */
    void printList() {
        for (Person p : personList) {
            System.out.println(p);
        }
    }

    void sort(IPersonComparator comparator) {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(personList[j], personList[j + 1])) {
                    Person p = personList[j];
                    personList[j] = personList[j + 1];
                    personList[j + 1] = p;
                }
            }
        }
    }

    /**
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.
     * @param sorter to determine the sorting method
     * @param comparator to determine the ordering variable
     */
    void sortByParams(IPersonListSorter sorter, IPersonComparator comparator) {
        this.personList = sorter.sort(this.personList, this.size, comparator);
    }

}
