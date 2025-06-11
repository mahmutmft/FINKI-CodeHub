package listi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex4 {
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
            if (node.length()>L){
                updatedList.add("Outline");
            }
            updatedList.add(node);
        }
        ListPrinter.printStringList(updatedList);
    }
}
