package checkers.impls;

import checkers.IPersonChecker;
import models.Person;

public class FioPersonChecker implements IPersonChecker {
    @Override
    public boolean check(Person p, Object value) {
        return p.getName().equals(value);
    }
}
