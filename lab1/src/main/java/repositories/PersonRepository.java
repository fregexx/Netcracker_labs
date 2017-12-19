package repositories;

import checkers.IChecker;
import comparator.IComparator;
import models.Person;
import org.joda.time.LocalDate;
import sorters.ISorter;

public class PersonRepository extends Repository<Person>{

    public PersonRepository(){
        this.list = new Person[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * if this list contains the specified persons
     *
     * @param checker to determine the sorting property
     * @param value   whose presence in this list is to be tested
     */
    private PersonRepository search(IChecker checker, Object value){
        PersonRepository found = new PersonRepository();
        for(Person person: this){
            if(checker.check(person, value)){
                found.add(person);
            }
        }
        return found;
    }

    /**
     * if this repository contains persons with specified name
     *
     * @param value whose presence in this list is to be tested
     * @return PersonRepository with list of {@link Person} found
     */
    public PersonRepository searchByName(String value) {
        return search(new IChecker(){
            @Override
            public boolean check(Object p, Object value) {
                return ((Person)p).getName().equals(value);
            }
        }, value);
    }

    /**
     * if this repository contains persons with specified birthdate
     *
     * @param value whose presence in this list is to be tested
     * @return PersonRepository with list of {@link Person} found
     */
    public PersonRepository searchByBirthDate(String value) {
        return search((p, v)-> ((Person)p).getDateOfBirth().equals(LocalDate.parse(value.toString())), value);
    }

    /**
     * Sorts the array of persons by name
     * use {@link #setSorter(ISorter)} )} method to determine the sorting method
     */
    public void sortByName() {
        sorter.sort(list, size, new IComparator<Person>() {
            @Override
            public int compare(Person item1, Person item2) {
                return item1.getName().compareTo(item2.getName());
            }
        });
    }

    /**
     * Sorts the array of cars by birthdate
     * use {@link #setSorter(ISorter)} )} method to determine the sorting method
     */
    public void sortByBirthdate() {
        sorter.sort(list, size, (person1,person2)->((Person)person1).getDateOfBirth().compareTo(((Person)person2).getDateOfBirth()));
    }
}
