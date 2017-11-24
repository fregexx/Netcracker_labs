import org.joda.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PersonRepository list = new PersonRepository();

        list.insert(new Person(10,"Ivanov Ivan","1999-01-01"));
        list.insert(new Person(1, "Some Name", "1956-10-17"));
        list.insert(new Person(2, "Other Name", "1915-07-07"));
        list.insert(new Person(3, "Donald Trump", "1977-02-10"));
        list.insert(new Person(4, "Alice Fox", "1997-12-29"));
        list.insert(new Person(5, "Adam Barter", "1988-03-18"));
        list.insert(new Person(7, "James Bond", "1965-01-28"));
        list.insert(new Person(8, "Alex Smith", "1974-07-17"));
        list.insert(new Person(9, "Michel LastName", "1961-09-20"));


        list.remove(2);
        list.remove(3);

        list.printList();

        System.out.println("sorted");

 //       list.sort(new ComparePersonsById());
//        list.sort(new ComparePersonByName());

//        list.sortPersonListByProperty(ArraySortProperty.SELECTION, new ComparePersonsByName());
//        list.sortPersonListByProperty(ArraySortProperty.SELECTION, new ComparePersonsByDOB());
//        list.sortByParams(new BubbleSort(), new ComparePersonsById());
//        list.sortByParams(new SelectionSort(), new ComparePersonsByName());
        list.sortByParams(new InsertionSort(), new ComparePersonsByDOB());

        list.printList();
    }
}
