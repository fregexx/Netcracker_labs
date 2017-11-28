import static java.lang.Integer.*;

public class FindById implements IPersonFinder<Integer>{

    @Override
    public <Integer>Person[] find(Integer key, Person[] personList) {
        int size = 0;
        Person[] found = new Person[size];
        for(Person p: personList){
            if(valueOf(p.getId())==key){
                Person[] tmp = new Person[++size];
                for(int i=0; i < size-1; i++){
                    tmp[i] = found[i];
                    tmp[i+1] = p;
                }
                tmp[size-1] = p;
                found = tmp;
            }
        }
        return found;
    }
}
