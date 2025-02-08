package main.java.zadaciZaPrvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class zadaca5 {
    public static int count(int n) {
        int counter = 0;
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> cards = new LinkedList<>();
        for (int i = 1; i <= 51; i++) {
            cards.offer(i);
        }
        while (cards.peek() != n) {
            for (int i = 0; i < 7; i++) {
                stack.push(cards.poll());
            }
            while (!stack.isEmpty()) {
                cards.offer(stack.pop());
                int toBack = cards.poll();
                cards.offer(toBack);
            }
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
