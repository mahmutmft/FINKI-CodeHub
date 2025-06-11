package podatocniStrukturi;

import java.util.*;

public class ex15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        int counter = 0;
        for (int i = 1; i <=51; i++) {
            deque.addLast(i);
        }
        while (deque.peek()!=n){
            for (int i = 0; i < 7; i++) {
                stack.push(deque.removeFirst());
            }
            while (!stack.isEmpty()){
                deque.addLast(stack.pop());
                deque.addLast(deque.removeFirst());
            }
            counter++;
        }
        System.out.println(counter);
    }
}
