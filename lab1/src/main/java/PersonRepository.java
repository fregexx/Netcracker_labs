public class PersonRepository {

    private Person[] personList;
    private int count;

    public PersonRepository() {
        this.personList = null;
        this.count = 0;
    }

    void insert(Person person) {
        if (count == 0) {
            personList = new Person[1];
            count++;
            personList[0] = person;
        } else {
            Person[] newList = new Person[count + 1];
            for (int i = 0; i < personList.length; i++) {
                newList[i] = personList[i];
            }
            newList[count] = person;
            count++;
            this.personList = newList;
        }

    }

    void remove(int id) {
        boolean found = false;
        int k = 0;
        for (Person p : personList) {
            if (id == p.getId()) {
                found = true;
                break;
            }
            k++;
        }
        if (found) {
            Person[] newList = new Person[count - 1];
            for (int i = 0; i < k; i++) {
                newList[i] = personList[i];
            }
            for(int i=k+1; i< count; i++){
                newList[i-1] = personList[i];
            }
            personList = newList;
        }
    }

    Person[] getList() {
        return personList;
    }

    void printList(){
        for(Person p: personList){
            System.out.println(p.getName());
        }
    }
}
