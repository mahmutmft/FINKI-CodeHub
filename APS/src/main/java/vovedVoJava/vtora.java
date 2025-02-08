package main.java.vovedVoJava;

import java.util.Scanner;

public class vtora {
    public static void printReversed(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = (words[i].length()-1); j >=0; j--) {
                System.out.print(words[i].charAt(j));
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] words = new String[n];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }
        printReversed(words);
    }
}
