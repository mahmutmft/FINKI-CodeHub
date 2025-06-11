package listi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextLine());
        }
        int L = scanner.nextInt();
        ListPrinter.printStringList(list);
        List<String> updatedList = new ArrayList<>();
        for (String node : list){
            updatedList.add(node);
            if (node.length() == L){
                updatedList.add("Target");
            }
        }
        ListPrinter.printStringList(updatedList);
    }
}
