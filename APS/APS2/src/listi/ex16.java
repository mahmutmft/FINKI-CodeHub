package listi;

import java.util.Scanner;

public class ex16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        DLL<String> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextLine());
        }
        System.out.println(list);
        DLLNode<String> node = list.getFirst();
        DLLNode<String> nodeHelper;
        for (int i = 0; i < n; i++) {
            char firstOne = node.element.charAt(0);
            if (Character.isLowerCase(firstOne)){
                nodeHelper = node;
                list.delete(node);
                list.insertLast(nodeHelper.element);
            }
            node = node.succ;
        }
        System.out.println(list);
    }
}
