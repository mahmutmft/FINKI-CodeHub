package main.java.zadaciOdKnigata;

import java.util.*;

import main.resources.teacher.*;

public class zadaca1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<Integer> list = new SLL<>();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            list.insertLast(number);
            if (number > max) max = number;
            if (number < min) min = number;
        }

        SLL<Integer> listCloserToMin = new SLL<>();
        SLL<Integer> listCloserToMax = new SLL<>();

        SLLNode<Integer> node = list.getFirst();
        while (node != null) {
            int element = node.element;

            int distanceToMin = Math.abs(element - min);
            int distanceToMax = Math.abs(element - max);

            if (distanceToMin  <= distanceToMax) {
                listCloserToMin.insertLast(node.element);
            } else {
                listCloserToMax.insertLast(node.element);
            }
            node = node.succ;
        }

        System.out.println(listCloserToMin);
        System.out.println(listCloserToMax);

    }
}
