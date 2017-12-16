package checkers.impls;

import checkers.IPersonChecker;
import models.Person;

public class IdPersonChecker implements IPersonChecker {
    @Override
    public boolean check(Person p, Object value) {
        return Integer.valueOf(p.getId()).equals(value);
    }
}
