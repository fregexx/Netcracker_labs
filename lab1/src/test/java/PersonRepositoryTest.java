import Annotations.Injector;
import models.Person;
import org.junit.Before;
import org.junit.Test;
import repositories.PersonRepository;
import sorters.impls.BubbleSorter;
import sorters.impls.InsertionSorter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryTest {

    private Person p1;
    private Person p2;
    private Person p3;
    private Person p4;
    private Person p5;

    @Before
    public void initData() {
        p1 = new Person("Alex Smith", "1974-07-17");
        p2 = new Person("Alice Fox", "1997-12-29");
        p3 = new Person("James Bond", "1965-01-28");
        p4 = new Person("Adam Barter", "1988-03-18");
        p5 = new Person("James Martin", "1965-01-28");
    }

    @Test
    public void testXMLExportImport(){
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.exportDataToXML();

        PersonRepository expected = new PersonRepository();
        expected.importDataFromXML();

        assertArrayEquals(actual.getAll(), expected.getAll());
    }

    @Test
    public void testInjection() {
        PersonRepository actual = (new Injector()).inject(new PersonRepository());
        assertEquals(actual.getSorter().getClass().getSimpleName(), "InsertionSorter");
    }

    @Test
    public void testSize() {
        PersonRepository repository = new PersonRepository();
        repository.add(p1);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void testIsEmpty() {
        PersonRepository repository = new PersonRepository();
        assertTrue(repository.isEmpty());
    }

    @Test
    public void testAdd() {

        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        Person[] expected = new Person[10];
        expected[0] = p1;
        expected[1] = p2;
        expected[2] = p3;

        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testGet() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertEquals(actual.get(1), p2);
    }

    @Test
    public void testSet() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.set(1, p5);

        Person[] expected = new Person[10];
        expected[0] = p1;
        expected[1] = p5;
        expected[2] = p3;

        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testSetAll() {
        PersonRepository repository = new PersonRepository();

        Person[] expected = new Person[10];
        expected[0] = p1;
        expected[1] = p5;
        expected[2] = p3;
        repository.setAll(expected);

        assertArrayEquals(repository.getAll(), expected);
    }

    @Test
    public void testContains() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertTrue(actual.contains(p3));
    }

    @Test
    public void testRemove() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.remove(2);

        Person[] expected = new Person[2];
        expected[0] = p1;
        expected[1] = p2;

        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testIndexOf() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertEquals(actual.indexOf(p3), 2);
    }

    @Test
    public void testBubbleSortByName() {
        PersonRepository actual = (new Injector()).inject(new PersonRepository());
        actual.setSorter(new BubbleSorter());
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.add(p5);
        actual.sortByName();

        Person[] expected = new Person[10];
        expected[0] = p4;
        expected[1] = p2;
        expected[2] = p3;
        expected[3] = p5;

        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testInsertionSortByBirthdate() {
        PersonRepository actual = (new Injector()).inject(new PersonRepository());
        actual.setSorter(new InsertionSorter());
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.sortByBirthdate();

        Person[] expected = new Person[10];
        expected[0] = p3;
        expected[1] = p1;
        expected[2] = p4;
        expected[3] = p2;
        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testSearchByName() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p5);

        PersonRepository found = actual.searchByName("James Martin");

        Person[] expected = new Person[10];
        expected[0] = p5;
        assertArrayEquals(found.getAll(), expected);
    }

    @Test
    public void testSearchByBirthDate() {
        PersonRepository actual = new PersonRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);

        PersonRepository found = actual.searchByBirthDate("1997-12-29");

        Person[] expected = new Person[10];
        expected[0] = p2;

        assertArrayEquals(found.getAll(), expected);
    }

}
