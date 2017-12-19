package repositories;

import checkers.IChecker;
import comparator.IComparator;
import models.Car;
import sorters.ISorter;

public class CarRepository extends Repository<Car> {

    public CarRepository(){
        this.list = new Car[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * if this list contains the specified cars
     *
     * @param checker to determine the sorting property
     * @param value   whose presence in this list is to be tested
     */
    private CarRepository search(IChecker checker, Object value){
        CarRepository found = new CarRepository();
        for(Car car: this){
            if(checker.check(car, value)){
                found.add(car);
            }
        }
        return found;
    }

    /**
     * if this repository contains cars with specified model
     *
     * @param value whose presence in this list is to be tested
     * @return CarRepository with list of {@link Car} found
     */
    public CarRepository searchByModel(String value) {
        return search(new IChecker(){
            @Override
            public boolean check(Object p, Object value) {
                return ((Car)p).getModel().equals(value);
            }
        }, value);
    }

    /**
     * if this repository contains cars with specified color
     *
     * @param value whose presence in this list is to be tested
     * @return CarRepository with list of {@link Car} found
     */
    public CarRepository searchByColor(String value) {
        return search((p, v)-> ((Car)p).getColor().equals(v), value);
    }

    /**
     * Sorts the array of cars by model
     * use {@link #setSorter(ISorter)} )} method to determine the sorting method
     */
    public void sortByModel() {
        sorter.sort(list, size, new IComparator<Car>() {
            @Override
            public int compare(Car item1, Car item2) {
                return item1.getModel().compareTo(item2.getModel());
            }
        });
    }

    /**
     * Sorts the array of cars by color
     * use {@link #setSorter(ISorter)} )} method to determine the sorting method
     */
    public void sortByColor() {
        sorter.sort(list, size, (car1,car2)->((Car)car1).getColor().compareTo(((Car)car2).getColor()));
    }
}