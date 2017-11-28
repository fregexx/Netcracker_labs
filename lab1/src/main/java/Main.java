import org.joda.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PersonRepository personRepository = new PersonRepository();

        personRepository.insert(new Person(10,"Ivanov Ivan","1999-01-01"));
        personRepository.insert(new Person(1, "Some Name", "1956-10-17"));
        personRepository.insert(new Person(2, "Other Name", "1915-07-07"));
        personRepository.insert(new Person(3, "Donald Trump", "1977-02-10"));
        personRepository.insert(new Person(4, "Alice Fox", "1997-12-29"));
        personRepository.insert(new Person(5, "Adam Barter", "1988-03-18"));
        personRepository.insert(new Person(7, "James Bond", "1965-01-28"));
        personRepository.insert(new Person(8, "Alex Smith", "1974-07-17"));
        personRepository.insert(new Person(9, "Michel LastName", "1961-09-20"));
        personRepository.insert(new Person(7, "Jack Richer", "1915-07-07"));
        personRepository.insert(new Person(6, "Some Name", "1941-09-23"));


        personRepository.remove(2);
        personRepository.remove(3);

        personRepository.printList();

//        list.find(new FindById(), 7);
        personRepository.find(new FindById(), "7");
//        list.find(new FindByName(),"Some Name");

        System.out.println("sorted");

        //       list.sort(new ComparePersonsById());
//        list.sort(new ComparePersonByName());

//        list.sortPersonListByProperty(ArraySortProperty.SELECTION, new ComparePersonsByName());
//        list.sortPersonListByProperty(ArraySortProperty.SELECTION, new ComparePersonsByDOB());
        personRepository.sortByParams(new BubbleSort(), new ComparePersonsById());
//        list.sortByParams(new SelectionSort(), new ComparePersonsByName());
//        list.sortByParams(new InsertionSort(), new ComparePersonsByDOB());

        personRepository.printList();
    }
}
