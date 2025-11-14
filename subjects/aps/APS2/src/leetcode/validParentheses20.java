package leetcode;

import java.util.Scanner;
import java.util.Stack;

public class validParentheses20 {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char character : s.toCharArray()) {
            if (character == '(' || character == '{' || character == '[') {
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (character == ')' && top != '(') return false;
                else if (character == ']' && top != '[') return false;
                else if (character == '}' && top != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(isValid(scanner.nextLine()));
    }
}
