package listi;

import java.util.LinkedList;
import java.util.Scanner;

public class ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        SLL<String> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextLine());
        }
        System.out.println(list);
        int L = scanner.nextInt();
        list.mirror();
        SLLNode<String> node = list.getFirst();
        while (node!=null){
            if (node.element.length() == L){
                list.insertLast(node.element);
                list.delete(node);
                break;
            }
            node = node.succ;
        }
        list.mirror();
        System.out.println(list);
    }

}
