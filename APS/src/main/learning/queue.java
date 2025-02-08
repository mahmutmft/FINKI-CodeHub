package main.learning;

import java.util.LinkedList;
import java.util.Queue;

public class queue {

    public static void main(String[] args) {
        // Create a queue using LinkedList
        Queue<String> queue = new LinkedList<>();

        // Check if the queue is empty (initially it should be empty)
        System.out.println("Is the queue empty? " + queue.isEmpty()); // true

        // Add elements to the queue using offer()
        queue.offer("Stefan"); // Adds "Stefan" to the queue
        queue.offer("Nikola");  // Adds "Nikola" to the queue
        queue.offer("Aco"); // Adds "Aco" to the queue
        queue.offer("Spase"); // Adds "Spase" to the queue

        // Check if the queue is empty again (now it should not be empty)
        System.out.println("Is the queue empty? " + queue.isEmpty()); // false

        // Get the size of the queue
        System.out.println("Size of the queue: " + queue.size()); // 4

        // Check if the queue contains a specific element
        System.out.println("Does the queue contain 'Spase'? " + queue.contains("Spase")); // true

        // Peek at the front element of the queue (without removing it)
        System.out.println("Front element of the queue: " + queue.peek()); // "Stefan"

        // Remove elements from the queue using poll()
        System.out.println("Removed element: " + queue.poll()); // Removes and returns "Stefan"
        System.out.println("Removed element: " + queue.poll()); // Removes and returns "Nikola"

        // Print the remaining elements in the queue
        System.out.println("Queue after polling two elements: " + queue); // [Aco, Spase]

        // Peek at the front element again
        System.out.println("Front element of the queue after polling: " + queue.peek()); // "Aco"

        // Remove all elements from the queue
        queue.clear();
        System.out.println("Queue after clearing: " + queue); // []

        // Check if the queue is empty after clearing
        System.out.println("Is the queue empty after clearing? " + queue.isEmpty()); // true
    }
}