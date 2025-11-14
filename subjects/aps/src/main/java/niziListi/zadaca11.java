package main.java.niziListi;
import java.util.*;
import main.resources.teacher.*;

public class zadaca11 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        System.out.println(list);
        DLLNode<Integer> node = list.getLast();
        for (int i = 0; i <k ; i++) {
            list.insertFirst(node.element);
            list.delete(node);
            node = node.pred;
        }
        System.out.println(list);
    }
}
