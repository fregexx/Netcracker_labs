import org.joda.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1, "first", LocalDate.parse("1956-10-17"));
        Person p2 = new Person(2, "second", LocalDate.parse("1915-07-07"));
        Person p3 = new Person(3, "third", LocalDate.parse("1977-02-10"));
        PersonRepository list = new PersonRepository();

        list.insert(p1);
        list.insert(p2);
        list.insert(p3);

        list.remove(2);

        list.printList();
    }
}
