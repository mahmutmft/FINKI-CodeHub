package main.java.heshiranje;

import java.util.*;

public class zadaca2 {
    static class User {
        String name;
        String username;
        int budget;
        String ip;
        String city;
        int ticketPrice;

        public User(String name, String username, int budget, String ip, String city, int ticketPrice) {
            this.name = name;
            this.username = username;
            this.budget = budget;
            this.ip = ip;
            this.city = city;
            this.ticketPrice = ticketPrice;
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Map<String, List<User>> cityMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String username = scanner.next();
            int budget = scanner.nextInt();
            String ip = scanner.next();
            String time = scanner.next();
            String city = scanner.next();
            int ticketPrice = scanner.nextInt();
            User user = new User(name, username, budget, ip, city, ticketPrice);
            cityMap.putIfAbsent(city, new ArrayList<>());
            cityMap.get(city).add(user);
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
            int ticketPrice = scanner.nextInt();

            User testUser = new User(name, username, budget, ip, city, ticketPrice);
            System.out.println("City: " + city + " has the following number of customers:");
            List<User> users = cityMap.get(city);
            if (users!=null){
                int count = 0;
                int maxSpent = 0;
                User topSpender = null;
                for (User user : users){
                    if (user.ticketPrice<=user.budget){
                        count++;
                        if (user.ticketPrice>maxSpent){
                            maxSpent = user.ticketPrice;
                            topSpender = user;
                        }
                    }
                }
                System.out.println(count);
                if (topSpender!=null){
                    System.out.println("The user who spent the most purchasing for that city is:");
                    System.out.println(topSpender.name + " " +topSpender.username + " with salary " + topSpender.budget + " from address " + topSpender.ip + " who spent " + maxSpent);

                }
                System.out.println();
            }
        }
    }
}