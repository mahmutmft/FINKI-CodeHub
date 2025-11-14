package leetcode;

import java.util.Scanner;
import java.util.Stack;

public class reversePrefixWord2000 {
    public static String reversePrefix(String word, char ch) {
        Stack<Character> stack = new Stack<>();
        StringBuilder reversedPart = new StringBuilder();
        boolean prefixReversed = false;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == ch && !prefixReversed) {
                stack.push(currentChar);
                while (!stack.isEmpty()) {
                    reversedPart.append(stack.pop());
                }
                prefixReversed = true;
            } else {
                stack.push(currentChar);
            }
        }
        StringBuilder remainingPart = new StringBuilder();
        while (!stack.isEmpty()) {
            remainingPart.append(stack.pop());
        }
        remainingPart.reverse();
        reversedPart.append(remainingPart);

        return reversedPart.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        char letter = scanner.next().charAt(0);
        System.out.println(reversePrefix(word, letter));
    }
}
