package main.java.vovedVoJava;

import java.util.*;

public class prva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int[] arrayFirstZero = new int[n];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            if (array[i] == 0) {
                arrayFirstZero[i] = array[i];
                counter++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (array[i] != 0) {
                arrayFirstZero[counter] = array[i];
                counter++;
            }
        }
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++) {
            System.out.print(arrayFirstZero[i] + " ");
        }
    }
}
