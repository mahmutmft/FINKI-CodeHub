package main.java.niziListi;
import java.util.*;
import main.resources.teacher.*;

public class osma {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            DLLNode<Integer> node = list.getFirst();
            list.delete(node);
            list.insertLast(node.element);
        }
        System.out.println(list);
    }
}
