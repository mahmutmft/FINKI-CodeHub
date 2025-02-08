package main.java.niziListi;

import java.util.*;

public class prva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        int[] array = new int[n];

        System.out.print("{");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            sum += array[i];
            if (i == n - 1)
                System.out.print(array[i] + "}");
            else
                System.out.print(array[i] + ",");
        }

        double avg = (double) sum / (double) n;
        System.out.println();

        List<Integer> second = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (array[i] >= avg) {
                second.add(array[i]);
            }
        }

        System.out.print("{");
        for (int i = 0; i < second.size(); i++) {
            if (i == second.size() - 1)
                System.out.print(second.get(i) + "}");
            else
                System.out.print(second.get(i) + ",");
        }
    }
}
