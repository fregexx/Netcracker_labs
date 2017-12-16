
import models.Person;
import org.junit.Before;
import repositories.PersonRepository;
import org.junit.Test;
import sorters.impls.BubbleSorter;
import sorters.impls.InsertionSorter;
import sorters.impls.SelectionSorter;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonRepositoryTest {

    private Person p1;
    private Person p2;
    private Person p3;
    private Person p4;
    private Person p5;

    @Before
    public void initData(){
        p1 = new Person(8, "Alex Smith", "1974-07-17");
        p2 = new Person(4, "Alice Fox", "1997-12-29");
        p3 = new Person(7, "James Bond", "1965-01-28");
        p4 = new Person(5, "Adam Barter", "1988-03-18");
        p5 = new Person(3, "James Martin", "1965-01-28" );
    }

    @Test
    public void testSize(){
        PersonRepository repository = new PersonRepository();
        repository.add(p1);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void testIsEmpty(){
        PersonRepository repository = new PersonRepository();
        assertTrue(repository.isEmpty());
    }

    @Test
    public void testAdd(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        PersonRepository expected = new PersonRepository();
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);

        assertArrayEquals(actual.getAll(),expected.getAll());
    }

    @Test
    public void testGet(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertEquals(actual.get(1),p2);
    }

    @Test
    public void testSet(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.set(1, p5);

        PersonRepository expected = new PersonRepository();
        expected.add(p1);
        expected.add(p5);
        expected.add(p3);

        assertArrayEquals(actual.getAll(),expected.getAll());
    }

    @Test
    public void testContains(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertTrue(actual.contains(p3));
    }

    @Test
    public void testRemove(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.remove(2);

        PersonRepository expected = new PersonRepository();
        expected.add(p1);
        expected.add(p2);

        assertEquals(actual.size(), expected.size());
        assertEquals(actual.getAll()[0], expected.getAll()[0]);
        assertEquals(actual.getAll()[1], expected.getAll()[1]);
    }

    @Test
    public void testIndexOf(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertEquals(actual.indexOf(p3),2);
    }

    @Test
    public void testBubbleSortById(){
        PersonRepository actual = new PersonRepository();
        actual.setSorter(new BubbleSorter());
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.sortById();

        PersonRepository expected = new PersonRepository();
        expected.add(p2);
        expected.add(p4);
        expected.add(p3);
        expected.add(p1);

        assertArrayEquals(actual.getAll(),expected.getAll());
    }

    @Test
    public void testInsertionSortByName(){
        PersonRepository actual = new PersonRepository();
        actual.setSorter(new InsertionSorter());
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.sortByName();

        PersonRepository expected = new PersonRepository();
        expected.add(p4);
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);

        assertArrayEquals(actual.getAll(),expected.getAll());
    }

    @Test
    public void testSelectionSortByDOB(){
        PersonRepository actual = new PersonRepository();
        actual.setSorter(new SelectionSorter());
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.sortByDOB();

        PersonRepository expected = new PersonRepository();
        expected.add(p3);
        expected.add(p1);
        expected.add(p4);
        expected.add(p2);

        assertArrayEquals(actual.getAll(),expected.getAll());
    }


    @Test
    public void testSearchByFIO(){
        PersonRepository personRepository = new PersonRepository();
        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);
        personRepository.add(p4);

        PersonRepository expected = new PersonRepository();
        expected.add(p2);

        PersonRepository found = personRepository.searchByFio("Alice Fox");

        assertArrayEquals(found.getAll(),expected.getAll());
    }

    @Test
    public void testSearchByDOB(){
        PersonRepository personRepository = new PersonRepository();
        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);
        personRepository.add(p5);

        PersonRepository expected = new PersonRepository();
        expected.add(p3);
        expected.add(p5);

        PersonRepository found = personRepository.searchByDOB("1965-01-28");

        assertArrayEquals(found.getAll(),expected.getAll());
    }

}
