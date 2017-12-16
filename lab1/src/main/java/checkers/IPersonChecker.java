package checkers;

import models.Person;

@FunctionalInterface
public interface IPersonChecker {
    public boolean check(Person p, Object value);
}
