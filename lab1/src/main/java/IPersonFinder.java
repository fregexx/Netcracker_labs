/**
 *
 * @param <T>
 */
public interface IPersonFinder<T> {
    <T>Person[] find(T key, Person[] personList);
}
