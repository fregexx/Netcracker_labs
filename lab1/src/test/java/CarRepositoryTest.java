import Annotations.Injector;
import models.Car;
import org.junit.Before;
import org.junit.Test;
import repositories.CarRepository;
import sorters.impls.BubbleSorter;
import sorters.impls.InsertionSorter;

import static org.junit.Assert.*;

public class CarRepositoryTest {

    private Car p1;
    private Car p2;
    private Car p3;
    private Car p4;
    private Car p5;
    private Car p6;

    @Before
    public void initData() {
        p1 = new Car("Lada Kalina", "Gray");
        p2 = new Car("Lada Granta", "Black");
        p3 = new Car("Renault Logan", "Green");
        p4 = new Car("Kia Rio", "Red");
        p5 = new Car("Chevrolet Camaro", "Orange");
        p6 = new Car("Chevrolet Camaro", "Blue");
    }

    @Test
    public void testInjection() {
        CarRepository actual = (new Injector()).inject(new CarRepository());
        assertEquals(actual.getSorter().getClass().getSimpleName(), "InsertionSorter");
    }

    @Test
    public void testSize() {
        CarRepository repository = new CarRepository();
        repository.add(p1);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void testIsEmpty() {
        CarRepository repository = new CarRepository();
        assertTrue(repository.isEmpty());
    }

    @Test
    public void testAdd() {
        CarRepository actual = new CarRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        Car[] expected = new Car[10];
        expected[0] = p1;
        expected[1] = p2;
        expected[2] = p3;

        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testGet() {
        CarRepository actual = new CarRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertEquals(actual.get(1), p2);
    }

    @Test
    public void testSet() {
        CarRepository actual = new CarRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.set(1, p5);

        Car[] expected = new Car[10];
        expected[0] = p1;
        expected[1] = p5;
        expected[2] = p3;
        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testContains() {
        CarRepository actual = new CarRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertTrue(actual.contains(p3));
    }

    @Test
    public void testIndexOf() {
        CarRepository actual = new CarRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);

        assertEquals(actual.indexOf(p3), 2);
    }

    @Test
    public void testBubbleSortByModel() {
        CarRepository actual = (new Injector()).inject(new CarRepository());
        actual.setSorter(new BubbleSorter());
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.sortByModel();

        Car[] expected = new Car[10];
        expected[0] = p4;
        expected[1] = p2;
        expected[2] = p1;
        expected[3] = p3;
        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testInsertionSortByColor() {
        CarRepository actual = (new Injector()).inject(new CarRepository());
        actual.setSorter(new InsertionSorter());
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.sortByColor();

        Car[] expected = new Car[10];
        expected[0] = p2;
        expected[1] = p1;
        expected[2] = p3;
        expected[3] = p4;
        assertArrayEquals(actual.getAll(), expected);
    }

    @Test
    public void testSearchByModel() {
        CarRepository actual = new CarRepository();
        actual.add(p5);
        actual.add(p2);
        actual.add(p3);
        actual.add(p6);

        CarRepository found = actual.searchByModel("Chevrolet Camaro");

        Car[] expected = new Car[10];
        expected[0] = p5;
        expected[1] = p6;
        assertArrayEquals(found.getAll(), expected);
    }

    @Test
    public void testSearchByColor() {
        CarRepository actual = new CarRepository();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);

        CarRepository found = actual.searchByColor("Gray");

        Car[] expected = new Car[10];
        expected[0] = p1;

        assertArrayEquals(found.getAll(), expected);
    }
}
