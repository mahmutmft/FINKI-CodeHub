package hash;

import java.util.*;

public class Rodendeni {
    static class Person {
        String name;
        String username;
        String birthDate;
        int age;

        public Person(String name, String username, String birthDate) {
            this.name = name;
            this.username = username;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, ArrayList<Person>> map = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String username = scanner.next();
            String birthDate = scanner.next();
            Person person = new Person(name, username, birthDate);

            int indexNumber = birthDate.lastIndexOf('/');
            String realDate = birthDate.substring(0, indexNumber);

            map.computeIfAbsent(realDate, k -> new ArrayList<>()).add(person);
            scanner.nextLine();
        }
        String date = scanner.nextLine();
        int indexNumber = date.lastIndexOf('/');
        String realDate = date.substring(0, indexNumber);
        String years = date.substring(indexNumber+1);
        ArrayList<Person> list = map.get(realDate);
        list.sort(Comparator.comparing(person -> person.name));
        for (Person person : list) {
            int index = person.birthDate.lastIndexOf('/');
            String dates = person.birthDate.substring(index+1);
            int yearsCount = Integer.parseInt(years) - Integer.parseInt(dates);
            person.age = yearsCount;
        }
//        list.sort(Comparator.comparing((Person person) -> person.age).reversed());

    }
}


// Test Case:
//Input:
//        4 // n
//        Magdalena Kostoska 15/05/1982  // Ime Prezime datum
//        Hristina Mihajloska 30/05/1984
//        Ilinka Ivanoska 15/05/1986
//        Anastas Mishev 30/05/1975
//        30/05/2015   // datum za koj treba da se ispecati koi lugje slavat rodenden na toj datum
//Output:
//        Anastas Mishev 40
//        Hristina Mihajloska 31

// Challenge: napraj da se sortiret po koj godini ne po ime