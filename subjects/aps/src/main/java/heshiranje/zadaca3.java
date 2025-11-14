package main.java.heshiranje;

import java.util.*;

public class zadaca3 {
    static class Person{
        String name;
        String username;
        int budget;
        String ip;
        String time;
        String city;
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
        Scanner scanner =  new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Map<String, List<Person>> hash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String username = scanner.next();
            int budget = scanner.nextInt();
            String ip = scanner.next();
            String time = scanner.next();
            String city = scanner.next();
            int price = scanner.nextInt();
            if(Integer.parseInt(time.split(":")[0]) > 11) {
                Person p = new Person(name, username, budget, ip, time, city, price);
                hash.putIfAbsent(city, new ArrayList<>());
                hash.get(city).add(p);
            }
        }
        scanner.nextLine();
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
            Person p = new Person(name,username,budget,ip,time,city,price);
            System.out.println("City: " + city + " has the following number of customers:");
            List<Person> users = hash.get(city);
            int count = 0;
            Person minPerson = users.get(0);
            for (Person person : users){
                String[] vreme = person.time.split(":");
                int hours = Integer.parseInt(vreme[0]) * 60;
                int minutes = hours + Integer.parseInt(vreme[1]);
                if (minutes>719){
                    count++;
                    int totalMinutesPerson = (Integer.parseInt(minPerson.time.split(":")[0]) * 60) + Integer.parseInt(minPerson.time.split(":")[1]);
                    if (totalMinutesPerson>minutes){
                        minPerson = person;
                    }
                }
            }
            System.out.println(count);
            System.out.println("The user who logged on earliest after noon from that city is:");
            System.out.println(minPerson.name + " " + minPerson.username + " with salary " + minPerson.budget + " from address " + minPerson.ip + " who logged in at " + minPerson.time);
            System.out.println();
        }
    }
}