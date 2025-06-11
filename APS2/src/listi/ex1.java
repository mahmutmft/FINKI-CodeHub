package listi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int sum = 0;
        System.out.print("{");
        boolean first = true;
        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            array[i] = number;
            sum+=number;
            if (first){
                System.out.print(number);
                first = false;
            }else {
                System.out.print("," + number);
            }

        }
        System.out.println("}");
        double average = (double) sum/n;
        List<Integer> list = new ArrayList<>();
        for (int num : array){
            if (num>=average){
                list.add(num);
            }
        }
        System.out.print("{");
        first = true;
        for (int num : list){
            if (first){
                System.out.print(num);
                first = false;
            }
            else{
                System.out.print("," + num);
            }

        }
        System.out.print("}");
    }
}
