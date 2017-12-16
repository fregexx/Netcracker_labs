package repositories;

import checkers.impls.DOBPersonChecker;
import checkers.impls.IdPersonChecker;
import comparator.impls.ComparePersonsByDOB;
import comparator.impls.ComparePersonsById;
import comparator.impls.ComparePersonsByName;
import models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.Configurator;
import checkers.impls.FioPersonChecker;
import checkers.IPersonChecker;
import sorters.IPersonListSorter;
import java.util.Iterator;

public class PersonRepository implements Iterable<Person> {

    private static final Logger LOGGER = LogManager.getLogger(PersonRepository.class.getName());
    private IPersonListSorter sorter = Configurator.getInstance().getSorter();

    private Person[] personList;

    /**
     * The size of the personList (the number of elements it contains).
     */
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 9;

    public PersonRepository() {
        this.personList = new Person[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public PersonRepository(int capacity) {
        this.personList = new Person[capacity];
        this.size = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        LOGGER.debug("size method called");
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        LOGGER.debug("isEmpty method called");
        return size == 0;
    }

    /**
     * Appends the specified Person element to the end of this list.
     *
     * @param person element to be appended to this list
     * @return true if success
     */
    public boolean add(Person person) {
        LOGGER.debug("add method of PersonRepository is called");
        if (size < personList.length) {
            personList[size++] = person;
        } else {
            if (MAX_CAPACITY - size > 0) {
                LOGGER.info("Repository size increased");
                Person[] newPersonList = new Person[size + 10];
                for (int i = 0; i < size; i++) {
                    newPersonList[i] = personList[i];
                }
                newPersonList[size++] = person;
                this.personList = newPersonList;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public Person get(int index) {

        LOGGER.debug("isEmpty method of PersonRepository is called");
        if (index < size) {
            return personList[index];
        } else {
            return null;
        }
    }

    /**
     * Returns an Array of Persons
     *
     * @return Array of Persons
     */
    public Person[] getAll() {
        LOGGER.debug("getAll method of PersonRepository is called");
        return personList;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index  index of the element to replace
     * @param person element to be stored at the specified position
     * @return true if success
     */
    public boolean set(int index, Person person) {
        LOGGER.debug("set method of PersonRepository is called");
        if (index < size && index >= 0) {
            this.personList[index] = person;
            return true;
        }
        return false;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param p element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(Person p) {
        return indexOf(p) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(Person p) {
        LOGGER.debug("indexOf method of PersonRepository is called");
        for (int i = 0; i < size; i++) {
            if (p.equals(personList[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     * @return true if the element was removed from the list
     */
    public boolean remove(int index) {
        LOGGER.debug("remove method of PersonRepository is called");
        if (index < size && index >= 0) {
            Person[] newPersonList = new Person[size - 1];
            for (int i = 0; i < index; i++) {
                newPersonList[i] = personList[i];
            }
            for (int i = index + 1; i < size; i++) {
                newPersonList[i - 1] = personList[i];
            }
            this.personList = newPersonList;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Sorts the specified array of objects by id
     * use {@link #setSorter(IPersonListSorter)} method to determine the sorting method
     */
    public void sortById() {
        LOGGER.debug("sortById method of PersonRepository is called");
        this.personList = sorter.sort(this.personList, size, new ComparePersonsById());
    }

    /**
     * Sorts the specified array of objects by name
     * use {@link #setSorter(IPersonListSorter)} method to determine the sorting method
     */
    public void sortByName() {
        LOGGER.debug("sortByName method of PersonRepository is called");
        this.personList = sorter.sort(this.personList, size, new ComparePersonsByName());
    }


    /**
     * Sorts the specified array of objects by birth date
     * use {@link #setSorter(IPersonListSorter)} method to determine the sorting method
     */
    public void sortByDOB() {
        LOGGER.debug("sortByDOB method of PersonRepository is called");
        this.personList = sorter.sort(this.personList, size, new ComparePersonsByDOB());
    }

    /**
     * Set sorting method to repository
     *
     * @param sorter
     */
    public void setSorter(IPersonListSorter sorter) {
        LOGGER.debug("setSorter method of PersonRepository is called");
        this.sorter = sorter;
    }

    /**
     * if this list contains the specified elements
     *
     * @param checker to determine the sorting property
     * @param value   whose presence in this list is to be tested
     */
    private PersonRepository search(IPersonChecker checker, Object value) {
        LOGGER.debug("search method of PersonRepository is called");
        PersonRepository found = new PersonRepository();
        for (Person p : this) {
            if (checker.check(p, value)) {
                found.add(p);
            }
        }
        return found;
    }

    /**
     * if this repository contains persons with specified id
     *
     * @param id whose presence in this list is to be tested
     * @return PersonRepository with list of Persons found
     */
    public PersonRepository searchById(int id) {
        LOGGER.debug("searchById method of PersonRepository is called");
        return search(new IdPersonChecker(), id);
    }

    /**
     * if this repository contains persons with specified name
     *
     * @param name whose presence in this list is to be tested
     * @return PersonRepository with list of Persons found
     */
    public PersonRepository searchByFio(String name) {
        LOGGER.debug("searchByFIO method of PersonRepository is called");
        return search(new FioPersonChecker(), name);
    }

    /**
     * if this repository contains persons with specified birthDate
     *
     * @param birthDate whose presence in this list is to be tested
     * @return PersonRepository with list of Persons found
     */
    public PersonRepository searchByDOB(String birthDate) {
        LOGGER.debug("searchByDOB method of PersonRepository is called");
        return search(new DOBPersonChecker(), birthDate);
    }

    @Override
    public Iterator<Person> iterator() {
        return new PersonrepositoryIterator();
    }

    class PersonrepositoryIterator implements Iterator<Person> {

        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex != size;
        }

        @Override
        public Person next() {
            return personList[currentIndex++];
        }
    }
}
