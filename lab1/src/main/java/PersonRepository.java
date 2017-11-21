public class PersonRepository {

    private Person[] personList;
    private int count;

    public PersonRepository() {
        this.personList = null;
        this.count = 0;
    }

    /**
     * Appends the specified Person element to the end of array
     * @param person element to be appended to this array
     */
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

    /**
     * Removes the Person element with specified id from the array
     * @param id Person id to search for
     */
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
            count--;
            personList = newList;
        }
    }

    /**
     * Returns an Array of Persons
     * @return Array of Persons
     */
    Person[] getList() {
        return personList;
    }

    /**
     *  Prints an Array of Person objects
     *  Terminates the line after each Person object
     */
    void printList(){
        for(Person p: personList){
            System.out.println(p);
 //           System.out.println(p.getName());
        }
    }
}
