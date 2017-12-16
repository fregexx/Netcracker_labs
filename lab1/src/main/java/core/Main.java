package core;

import models.Person;
import repositories.PersonRepository;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person(8, "Alex Smith", "1974-07-17");
        Person p2 = new Person(4, "Alice Fox", "1997-12-29");
        Person p3 = new Person(7, "James Bond", "1965-01-28");
        Person p4 = new Person(5, "Adam Barter", "1988-03-18");
        PersonRepository repository = new PersonRepository();
        repository.add(p1);
        repository.add(p2);
        repository.add(p3);
        repository.add(p4);
        for(Person p: repository){
            System.out.println(p);
        }
    }
}
