package main.java.zadaciOdKnigata;

import main.resources.teacher.SLL;
import main.resources.teacher.SLLNode;
import java.util.Scanner;

public class zadaca2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        SLLNode<Integer> node = list.getFirst();
        int k = 1;
        while (node!=null){
            int counter = 0;
            while (counter!=k && node !=null){
                counter++;
                node = node.succ;
            }
            list.delete(node);
            if (node==null){
                break;
            }
            node = node.succ;
            k++;
        }
        System.out.println(list);
    }
}