import org.joda.time.LocalDate;

import java.util.UUID;

public class Person {

        private int id;
        private String name;
        private LocalDate dateOfBirth;

        public Person(int id, String name, String dateOfBirth) {
            this.id = id;
            this.name = name;
            this.dateOfBirth = strToDate(dateOfBirth);
        }

        public int getAge(){
            return LocalDate.now().getYear() - dateOfBirth.getYear();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        private LocalDate strToDate(String date){
            return LocalDate.parse(date);
        }
    @Override
    public String toString() {
        return "Id = " + id +
                ", Name = '" + name + '\'' +
                ", Date of Birth = " + dateOfBirth.toString("dd MMMM yyyy");
    }
}

