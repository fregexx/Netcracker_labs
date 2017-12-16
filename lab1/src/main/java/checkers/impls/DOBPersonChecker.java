package checkers.impls;

import checkers.IPersonChecker;
import models.Person;
import org.joda.time.LocalDate;

public class DOBPersonChecker implements IPersonChecker {
    @Override
    public boolean check(Person p, Object value) {
        return p.getDateOfBirth().equals(LocalDate.parse(value.toString()));
    }
}
