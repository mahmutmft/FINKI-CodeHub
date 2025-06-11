package hash;

import java.util.*;

public class ex4 {
    static class Person {
        String name, username;
        int budget;
        String ip, time, city;
        int price;

        public Person(String name, String username, int budget, String ip, String time, String city, int price) {
            this.name = name;
            this.username = username;
            this.budget = budget;
            this.ip = ip;
            this.time = time;
            this.city = city;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Map<String, ArrayList<Person>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String username = scanner.next();
            int budget = scanner.nextInt();
            String ip = scanner.next();
            String time = scanner.next();
            String city = scanner.next();
            int price = scanner.nextInt();
            int lastDot = ip.lastIndexOf(".");
            String realIp = ip.substring(0, lastDot);
            Person person = new Person(name, username, budget, ip, time, city, price);
            map.computeIfAbsent(realIp, k -> new ArrayList<>()).add(person);
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            String name = scanner.next();
            String username = scanner.next();
            int budget = scanner.nextInt();
            String ip = scanner.next();
            String time = scanner.next();
            String city = scanner.next();
            int price = scanner.nextInt();
            int lastDot = ip.lastIndexOf(".");
            String realIp = ip.substring(0, lastDot);
            Person testCase = new Person(name, username, budget, ip, time, city, price);
            ArrayList<Person> list = map.get(realIp);
            if (!list.isEmpty()) {
                int counter = 0;
                Person topSpender = null;
                for (Person person : list) {
                    if (person.budget >= person.price) {
                        counter++;
                        if (topSpender == null || person.price > topSpender.price) {
                            topSpender = person;
                        }
                    }
                }
                System.out.println("IP network: " + realIp + " has the following number of users:");
                System.out.println(counter);
                System.out.println("The user who spent the most from that network is:");
                System.out.println(topSpender.name + " " + topSpender.username + " with salary "+topSpender.budget+ " from address " + topSpender.ip + " who spent " + topSpender.price);
            }
        }
    }
}
