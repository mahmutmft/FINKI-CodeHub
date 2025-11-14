package main.java.niziListi;
import java.util.*;
import main.resources.teacher.*;

public class shesta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<String> list = new SLL<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextLine());
        }
        int L = scanner.nextInt();
        System.out.println(list);
        SLLNode<String> node = list.getFirst();
        SLLNode<String> holder = list.getFirst();
        int counter = 0;
        while (node != null) {
            counter++;
            if (node.element.length() == L) {
                holder = node;
            }
            if (counter == n) {
                list.insertFirst(holder.element);
                list.delete(holder);
            }
            node = node.succ;
        }
        System.out.println(list);
    }
}
