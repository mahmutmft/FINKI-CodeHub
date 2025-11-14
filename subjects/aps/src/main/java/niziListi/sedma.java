package main.java.niziListi;
import java.util.*;
import main.resources.teacher.*;

public class sedma {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        System.out.println(list);
        for (int i = 0; i < k; i++) {
            DLLNode<Integer> node = list.getLast();
            list.delete(node);
            list.insertFirst(node.element);
        }
        System.out.println(list);
    }
}
