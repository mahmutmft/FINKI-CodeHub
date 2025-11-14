package main.java.vovedVoJava;

import java.util.Scanner;

public class prvaSoFunkcii {
    static void pushZerosToBeginning(int[] arr, int n) {
        int[] arr2 = new int[n];
        int counter = 0;
        for (int i=0;i<n;i++){
            if (arr[i] == 0){
                arr2[i] = arr[i];
                counter++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0 ){
                arr2[counter] = arr[i];
                counter++;
            }
        }
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr2[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        pushZerosToBeginning(array, n);
    }
}