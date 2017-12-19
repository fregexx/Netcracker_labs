package models;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.Objects;
import java.util.UUID;

public class Person {

    private UUID id;
    private String name;
    private LocalDate dateOfBirth;

    public Person(String name, String dateOfBirth) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.dateOfBirth = strToDate(dateOfBirth);
    }

    public int getAge() {
        Period period = new Period(dateOfBirth, LocalDate.now());
        return period.getYears();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private LocalDate strToDate(String date) {
        return LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "Id = " + id +
                ", Name = '" + name + '\'' +
                ", Date of Birth = " + dateOfBirth.toString("dd MMMM yyyy") +
                ", UID = " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, dateOfBirth);
    }
}

