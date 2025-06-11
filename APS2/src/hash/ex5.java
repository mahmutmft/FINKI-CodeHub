package hash;

import java.util.*;

public class ex5 {

    static class Person {
        String name, surname, ip, time, city;
        int budget, price, sumTime;

        public Person(String name, String surname, int budget, String ip, String time, String city, int price, int sumTime) {
            this.name = name;
            this.surname = surname;
            this.ip = ip;
            this.time = time;
            this.city = city;
            this.budget = budget;
            this.price = price;
            this.sumTime = sumTime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Map<String, ArrayList<Person>> hash = new HashMap<>();

        // Read initial people
        for (int i = 0; i < n; i++) {
            Person person = readPerson(scanner);
            String subnet = getSubnet(person.ip);
            hash.computeIfAbsent(subnet, k -> new ArrayList<>()).add(person);
        }

        int m = scanner.nextInt();
        scanner.nextLine();

        // Process next m users
        for (int i = 0; i < m; i++) {
            Person inputPerson = readPerson(scanner);
            String subnet = getSubnet(inputPerson.ip);

            ArrayList<Person> list = hash.getOrDefault(subnet, new ArrayList<>());
            int counter = 0;
            Person earliest = null;

            for (Person p : list) {
                if (p.sumTime >= 719) { // After noon
                    counter++;
                    if (earliest == null || p.sumTime < earliest.sumTime) {
                        earliest = p;
                    }
                }
            }

            System.out.println("IP network: " + subnet + " has the following number of users:");
            System.out.println(counter);

            if (earliest != null) {
                System.out.println("The user who logged on earliest after noon from that network is:");
                System.out.println(earliest.name + " " + earliest.surname + " with salary " + earliest.budget +
                        " from address " + earliest.ip + " who logged in at " + earliest.time);
            } else {
                System.out.println("No user from this network logged in after noon.");
            }
        }
    }

    // Helper method to read a Person object
    private static Person readPerson(Scanner scanner) {
        String name = scanner.next();
        String surname = scanner.next();
        int budget = scanner.nextInt();
        String ip = scanner.next();
        String time = scanner.next();
        String city = scanner.next();
        int price = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        int sumTime = convertTimeToMinutes(time);
        return new Person(name, surname, budget, ip, time, city, price, sumTime);
    }

    // Convert HH:MM to total minutes
    private static int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // Extract subnet from IP
    private static String getSubnet(String ip) {
        int index = ip.lastIndexOf(".");
        return ip.substring(0, index);
    }
}
