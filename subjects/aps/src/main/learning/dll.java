package main.learning;

import main.resources.teacher.DLL;
import main.resources.teacher.DLLNode;

public class dll {

    public static void main(String[] args) {
        // Create a doubly linked list to hold game titles
        DLL<String> gameList = new DLL<>();

        // Insert games at the beginning of the list
        gameList.insertFirst("Minecraft");
        gameList.insertFirst("Spider-Man");
        gameList.insertFirst("DOOM");

        // Print the entire list
        System.out.println("Current DLL: " + gameList);

        // Insert a game at the end of the list
        gameList.insertLast("Batman");
        System.out.println("After inserting Batman at the end: " + gameList);

        // Insert a game after a specific node
        DLLNode<String> doomNode = gameList.find("DOOM");
        if (doomNode != null) {
            gameList.insertAfter("Ghost Recon", doomNode);
            System.out.println("After inserting Ghost Recon after DOOM: " + gameList);
        } else {
            System.out.println("DOOM not found in the list.");
        }

        // Insert a game before a specific node
        DLLNode<String> batmanNode = gameList.find("Batman");
        if (batmanNode != null) {
            gameList.insertBefore("Cyberpunk 2077", batmanNode);
            System.out.println("After inserting Cyberpunk 2077 before Batman: " + gameList);
        } else {
            System.out.println("Batman not found in the list.");
        }

        // Delete the first game in the list
        String removedGame = gameList.deleteFirst();
        System.out.println("Removed first game: " + removedGame);
        System.out.println("DLL after deleting first game: " + gameList);

        // Delete a specific game from the list
        DLLNode<String> ghostReconNode = gameList.find("Ghost Recon");
        if (ghostReconNode != null) {
            String deletedGame = gameList.delete(ghostReconNode);
            System.out.println("Removed Ghost Recon: " + deletedGame);
            System.out.println("DLL after deleting Ghost Recon: " + gameList);
        } else {
            System.out.println("Ghost Recon not found in the list.");
        }

        // Find a game in the list
        String gameToSearch = "Batman";
        DLLNode<String> foundNode = gameList.find(gameToSearch);
        if (foundNode != null) {
            System.out.println(gameToSearch + " found in the list.");
        } else {
            System.out.println(gameToSearch + " not found in the list.");
        }

        // Check if the list is empty
        System.out.println("Is the DLL empty? " + (gameList.getSize() == 0));

        // Get the size of the list
        System.out.println("Size of the DLL: " + gameList.getSize());

        // Mirror the list (reverse it)
        gameList.mirror();
        System.out.println("DLL after mirroring: " + gameList);

        // Clear the entire list
        gameList.deleteList();
        System.out.println("DLL after clearing: " + gameList);

        // Demonstrate traversing the list using a while loop
        System.out.println("\nDemonstrating Node Traversal:");

        // Re-populate the list for traversal demonstration
        gameList.insertFirst("The Witcher");
        gameList.insertFirst("Skyrim");
        gameList.insertFirst("Fallout");
        gameList.insertFirst("Mass Effect");

        System.out.println("Current DLL for traversal: " + gameList);

        // Start traversal from the first node (forward traversal)
        DLLNode<String> currentNode = gameList.getFirst();
        int position = 1;

        System.out.println("\nForward Traversal:");
        while (currentNode != null) {
            System.out.println("Position " + position + ": " + currentNode.element);
            currentNode = currentNode.succ; // Move to the next node
            position++;
        }

        // Start traversal from the last node (backward traversal)
        currentNode = gameList.getLast();
        position = 1;

        System.out.println("\nBackward Traversal:");
        while (currentNode != null) {
            System.out.println("Position " + position + ": " + currentNode.element);
            currentNode = currentNode.pred; // Move to the previous node
            position++;
        }

        // Explain the DLL operations
        System.out.println("\nDLL (Doubly Linked List) Operations:");
        System.out.println("- Use insertFirst() to add elements at the beginning.");
        System.out.println("- Use insertLast() to add elements at the end.");
        System.out.println("- Use insertAfter() to add elements after a specific node.");
        System.out.println("- Use insertBefore() to add elements before a specific node.");
        System.out.println("- Use deleteFirst() to remove the first element.");
        System.out.println("- Use deleteLast() to remove the last element.");
        System.out.println("- Use delete() to remove a specific element.");
        System.out.println("- Use find() to search for an element.");
        System.out.println("- Use getSize() to get the number of elements in the list.");
        System.out.println("- Use mirror() to reverse the list.");
        System.out.println("- Use deleteList() to clear the entire list.");
        System.out.println("- Use a while loop to traverse the list forward or backward.");
    }
}