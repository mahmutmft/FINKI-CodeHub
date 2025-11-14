package main.java.niziListi;

import java.util.*;

import main.resources.teacher.*;

public class treta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        SLL<String> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextLine());
        }
        int L = scanner.nextInt();
        System.out.println(list);
        SLLNode<String> node = list.getFirst();
        while (node != null){
            if (node.element.length() == L){
                list.insertAfter("Target", node);
                node = node.succ;
            }
            node = node.succ;
        }
        System.out.println(list);
    }
}
