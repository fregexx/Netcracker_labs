import models.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    @Test
    public void testGetAge() {
        Person person = new Person("TestName","2000-01-01");
        assertEquals(17, person.getAge());
    }
}
