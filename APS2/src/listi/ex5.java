package listi;

import java.util.*;

public class ex5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            list.add(scanner.nextLine());
        }
        ListPrinter.printStringList(list);
        int L = scanner.nextInt();
        LinkedList<String> updatedList = new LinkedList<>();
        LinkedList<String> listHelper = new LinkedList<>();

        for (String node : list){
            if (node.length() == L){
                listHelper.add(node);
            }else {
                updatedList.add(node);
            }
        }
        updatedList.addAll(listHelper);
        ListPrinter.printStringList(updatedList);
    }
}
