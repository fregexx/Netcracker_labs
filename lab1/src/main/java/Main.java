import org.joda.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1, "First", LocalDate.parse("1956-10-17"));
        Person p2 = new Person(2, "Second", LocalDate.parse("1915-07-07"));
        Person p3 = new Person(3, "Third", LocalDate.parse("1977-02-10"));
        Person p4 = new Person(4, "Fourt", LocalDate.parse("1997-12-29"));
        Person p5 = new Person(5, "Fifth", LocalDate.parse("1988-03-18"));

        PersonRepository list = new PersonRepository();

        list.insert(p1);
        list.insert(p2);
        list.insert(p3);
        list.insert(p4);
        list.insert(p5);

        list.remove(2);
        list.remove(3);

        list.printList();
    }
}
