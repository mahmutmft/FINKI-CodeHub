package main.learning;

import java.util.Stack;

public class stack {

    public static void main(String[] args) {
        // Create a stack to hold game titles
        Stack<String> gameStack = new Stack<>();

        // Push games onto the stack
        gameStack.push("Minecraft");
        gameStack.push("Spider-Man");
        gameStack.push("DOOM");
        gameStack.push("Batman");
        gameStack.push("Ghost Recon");

        // Peek at the top element without removing it
        System.out.println("Top game on the stack: " + gameStack.peek());

        // Check if the stack is empty returns true or false
        System.out.println("Is the stack empty? " + gameStack.empty());

        // Print the entire stack
        System.out.println("Current stack: " + gameStack);

        // Search for a game in the stack
        String gameToSearch = "Minecraft";
        int position = gameStack.search(gameToSearch);
        if (position != -1) {
            System.out.println(gameToSearch + " found at position: " + position);
        } else {
            System.out.println(gameToSearch + " not found in the stack.");
        }

        // Search for a game that is not in the stack
        String nonExistentGame = "Something";
        int nonExistentPosition = gameStack.search(nonExistentGame);
        if (nonExistentPosition != -1) {
            System.out.println(nonExistentGame + " found at position: " + nonExistentPosition);
        } else {
            System.out.println(nonExistentGame + " not found in the stack.");
        }

        // Demonstrate popping an element from the stack
        String removedGame = gameStack.pop();
        System.out.println("Removed game: " + removedGame);
        System.out.println("Stack after pop: " + gameStack);

        // Explain the LIFO concept
        System.out.println("\nStack is a LIFO (Last-In First-Out) data structure:");
        System.out.println("- Use push() to add elements to the top.");
        System.out.println("- Use pop() to remove elements from the top.");
        System.out.println("- Use peek() to view the top element without removing it.");
        System.out.println("- Use search() to find the position of an element in the stack.");
    }
}