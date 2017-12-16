package comparator;

import models.Person;

/**
 * Compares its two arguments for order.  Returns a negative integer,
 * zero, or a positive integer as the first argument is less than, equal
 * to, or greater than the second.
 */
public interface IPersonComparator {
    int compare(Person person1, Person person2);
}
