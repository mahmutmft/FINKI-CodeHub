package main.java.zadaciZaPrvKolokvium;

import java.util.Scanner;

public class zadaca3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        final int card = 100;
        int min = n * card + Math.max(0, m - n) * card;
        int max = n * card + Math.max(0, m - 1) * card;
        System.out.println(min);
        System.out.println(max);
    }
}
