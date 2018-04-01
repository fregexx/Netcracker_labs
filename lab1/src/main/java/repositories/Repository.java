package repositories;

import Annotations.Inject;
import sorters.ISorter;

import java.util.Iterator;

public abstract class Repository<T> implements IRepository<T>, Iterable<T> {

    @Inject
    protected ISorter sorter;

    protected Object[] list;
    /**
     * The size of the personList (the number of elements it contains).
     */
    protected int size;
    protected static final int DEFAULT_CAPACITY = 10;
    protected static final int MAX_CAPACITY = Integer.MAX_VALUE - 9;

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified Person element to the end of this list.
     *
     * @param item element to be appended to this list
     * @return true if success
     */
    @Override
    public boolean add(T item) {
        if (size < list.length) {
            list[size++] = item;
        } else {
            if (MAX_CAPACITY - size > 0) {
                Object[] newList = new Object[size + 10];
                for (int i = 0; i < size; i++) {
                    newList[i] = list[i];
                }
                newList[size++] = item;
                this.list = newList;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     * @return true if the element was removed from the list
     */
    @Override
    public boolean remove(int index) {
        if (index < size && index >= 0) {
            Object[] newList = new Object[size - 1];
            for (int i = 0; i < index; i++) {
                newList[i] = list[i];
            }
            for (int i = index + 1; i < size; i++) {
                newList[i - 1] = list[i];
            }
            this.list = newList;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        if (index < size) {
            return (T) list[index];
        } else {
            return null;
        }
    }

    /**
     * Returns an Array of items in this repository
     *
     * @return Array of items
     */
    public T[] getAll() {
        return (T[]) list;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param item  element to be stored at the specified position
     * @return true if success
     */
    @Override
    public boolean set(int index, T item) {
        if (index < size && index >= 0) {
            this.list[index] = item;
            return true;
        }
        return false;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param item element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets sorting method to the repository
     *
     * @param sorter
     */
    public void setSorter(ISorter sorter) {
        this.sorter = sorter;
    }

    /**
     * Gets sorting method of the repository
     *
     * @return sorter
     */
    public ISorter getSorter() {
        return this.sorter;
    }

    @Override
    public Iterator<T> iterator() {
        return new RepositoryIterator();
    }

    class RepositoryIterator implements Iterator<T> {

        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex != size;
        }

        @Override
        public T next() {
            return (T) list[currentIndex++];
        }
    }
}
