package main.java.niziListi;
import main.resources.teacher.*;
import java.util.*;

public class cetvorta {
    public static void main(String[] args){
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
            if (node.element.length()>L){
                list.insertBefore("Outlier", node);
            }
            node = node.succ;
        }
        System.out.println(list);
    }
}
