package listi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> list = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextLine());
        }
        boolean first = true;
        for (String node : list) {
            if (first) {
                System.out.print(node.toString());
                first = false;
            } else {
                System.out.print("->" + node.toString());
            }
        }
        System.out.println();
        int L = scanner.nextInt();
        first = true;
        for (String node : list) {
            if (node.length() >= L) {
                if (first) {
                    System.out.print(node.toString());
                    first = false;
                } else {
                    System.out.print("->" + node.toString());
                }
            }
        }
    }
}
