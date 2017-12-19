package repositories;

import java.util.ArrayList;

public interface IRepository<T> {

    int size();
    boolean isEmpty();
    boolean add(T item);
    T get(int index);
    T[]getAll();
    boolean set(int index, T item);
    boolean contains(T item);
    int indexOf(T item);
    boolean remove(int index);
}
