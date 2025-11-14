package main.learning;

import java.util.HashMap;

public class hashmaps {

    public static void main(String[] args) {
        // Create a HashMap to store country-capital pairs
        HashMap<String, String> countries = new HashMap<>();

        // Insert countries and their capitals into the HashMap
        countries.put("USA", "Washington DC");
        countries.put("India", "New Delhi");
        countries.put("Macedonia", "Skopje");
        countries.put("China", "Beijing");
        countries.put("Japan", "Tokyo");
        countries.put("Germany", "Berlin");

        // Print the entire HashMap
        System.out.println("Initial HashMap: " + countries);

        // Retrieve the capital of a specific country
        System.out.println("Capital of Macedonia: " + countries.get("Macedonia"));

        // Check if the HashMap contains a specific key
        System.out.println("Does the HashMap contain 'Germany'? " + countries.containsKey("Germany")); // true

        // Check if the HashMap contains a specific value
        System.out.println("Does the HashMap contain 'Paris'? " + countries.containsValue("Paris")); // false
        System.out.println("Does the HashMap contain 'Skopje'? " + countries.containsValue("Skopje")); // true

        // Replace the capital of a specific country
        countries.replace("USA", "New York");
        System.out.println("HashMap after replacing USA's capital: " + countries);

        // Remove a country from the HashMap
        countries.remove("Japan");
        System.out.println("HashMap after removing Japan: " + countries);

        // Get the size of the HashMap
        System.out.println("Size of the HashMap: " + countries.size());

        // Iterate through the HashMap and print all key-value pairs
        System.out.println("\nIterating through the HashMap:");
        for (String country : countries.keySet()) {
            System.out.println(country + " -> " + countries.get(country));
        }

        // Clear the entire HashMap
        countries.clear();
        System.out.println("\nHashMap after clearing: " + countries);

        // Check if the HashMap is empty
        System.out.println("Is the HashMap empty? " + countries.isEmpty());

        // Explain the HashMap operations
        System.out.println("\nHashMap Operations:");
        System.out.println("- Use put(key, value) to add key-value pairs.");
        System.out.println("- Use get(key) to retrieve the value for a specific key.");
        System.out.println("- Use replace(key, newValue) to update the value for a specific key.");
        System.out.println("- Use remove(key) to remove a key-value pair.");
        System.out.println("- Use containsKey(key) to check if a key exists.");
        System.out.println("- Use containsValue(value) to check if a value exists.");
        System.out.println("- Use keySet() to iterate through all keys.");
        System.out.println("- Use clear() to remove all key-value pairs.");
        System.out.println("- Use size() to get the number of key-value pairs.");
        System.out.println("- Use isEmpty() to check if the HashMap is empty.");
    }
}