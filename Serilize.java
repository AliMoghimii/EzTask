import java.io.*;
import java.util.Vector;

class Person implements Serializable {

    String username;
    String email;
    String usertype;

    Person(String username, String email, String usertype){
        this.email = email;
        this.username = username;
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "name: " + username + " email: " + email + " usertype: " + usertype;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        Vector <Person> persons = new Vector<Person>();
        Person p = new Person("Alireza", "Arashidi", "dede");
        Person p2 = new Person("ded", "dede", "deed");

        persons.add(p);
        persons.add(p2);
        persons.add(new Person("kir", "ded", "dedesss"));

        File f1 = new File("D:\\dataStructure\\data3.txt");
        FileOutputStream outputStream = new FileOutputStream(f1);
        ObjectOutputStream outputStream1 = new ObjectOutputStream(outputStream);

        FileInputStream inputStream = new FileInputStream(f1);
        ObjectInputStream inputStream1 = new ObjectInputStream(inputStream);

        Vector<Person> getInfo;

        try {

            outputStream1.writeObject(persons);
            outputStream1.flush();

        }
        catch (IOException e) {
            System.out.println(e);
        }

        try {

            getInfo = new Vector<Person>((Vector<Person>)(inputStream1.readObject()));

            for (Person person : getInfo){
                System.out.println(person.toString());
            }

        }

        catch (Exception e){
            System.out.println(e);
        }


        finally {
            outputStream.close();
            outputStream1.close();
            inputStream.close();
            inputStream1.close();
        }

    }
}
