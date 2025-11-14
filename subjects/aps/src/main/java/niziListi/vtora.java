package main.java.niziListi;
import main.resources.teacher.*;
import java.util.*;

public class vtora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<String> list = new SLL<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String listElement = scanner.nextLine();
            list.insertLast(listElement);
        }
        int L = scanner.nextInt();
        System.out.println(list);
        SLLNode<String> node = list.getFirst();
        while (node != null) {
            if (node.element.length() < L) {
                list.delete(node);
            }
            node = node.succ;
        }
        System.out.println(list);
    }
}
