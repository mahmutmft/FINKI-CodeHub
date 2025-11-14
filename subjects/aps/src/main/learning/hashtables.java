package main.learning;

import java.util.Hashtable;
import java.util.Map;

public class hashtables {

    public static void main(String[] args) {
        // Create a hash table with an initial capacity of 10
        Hashtable<Integer, String> gameTable = new Hashtable<>(10);

        // Insert games into the hash table
        gameTable.put(100, "Minecraft");
        gameTable.put(123, "Spider-Man");
        gameTable.put(321, "DOOM");
        gameTable.put(555, "Batman");
        gameTable.put(777, "Cyberpunk 2077");

        // Print the entire hash table
        System.out.println("Current Hash Table: " + gameTable);

        // Retrieve a game by its key
        String game = gameTable.get(123);
        System.out.println("Game with key 123: " + game);

        // Remove a game by its key
        String removedGame = gameTable.remove(555);
        System.out.println("Removed game with key 555: " + removedGame);
        System.out.println("Hash Table after removal: " + gameTable);

        // Check if the hash table contains a specific key
        boolean containsKey = gameTable.containsKey(321);
        System.out.println("Does the hash table contain key 321? " + containsKey);

        // Check if the hash table contains a specific value
        boolean containsValue = gameTable.containsValue("DOOM");
        System.out.println("Does the hash table contain value 'DOOM'? " + containsValue);

        // Get the size of the hash table
        System.out.println("Size of the hash table: " + gameTable.size());

        // Traverse the hash table using keySet
        System.out.println("\nTraversing the Hash Table using keySet:");
        for (Integer key : gameTable.keySet()) {
            System.out.println("Key: " + key + ", Value: " + gameTable.get(key));
        }

        // Traverse the hash table using entrySet
        System.out.println("\nTraversing the Hash Table using entrySet:");
        for (Map.Entry<Integer, String> entry : gameTable.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Clear the entire hash table
        gameTable.clear();
        System.out.println("Hash Table after clearing: " + gameTable);

        // Explain the Hash Table operations
        System.out.println("\nHash Table Operations:");
        System.out.println("- Use put(key, value) to insert a key-value pair.");
        System.out.println("- Use get(key) to retrieve the value associated with a key.");
        System.out.println("- Use remove(key) to remove a key-value pair.");
        System.out.println("- Use containsKey(key) to check if a key exists.");
        System.out.println("- Use containsValue(value) to check if a value exists.");
        System.out.println("- Use size() to get the number of key-value pairs.");
        System.out.println("- Use keySet() to get all keys in the hash table.");
        System.out.println("- Use entrySet() to get all key-value pairs in the hash table.");
        System.out.println("- Use clear() to remove all key-value pairs from the hash table.");
    }
}