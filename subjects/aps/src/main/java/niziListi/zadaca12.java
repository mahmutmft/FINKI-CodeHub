package main.java.niziListi;
import main.resources.teacher.*;

import java.util.Scanner;

public class zadaca12 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        DLLNode<Integer> node = list.getFirst();
        System.out.println(list);
            for (int i = 0; i < k; i++) {
                list.insertLast(node.element);
                list.delete(node);
                node = node.succ;
            }
        System.out.println(list);
    }
}
