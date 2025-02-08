package main.java.zadaciZaPrvKolokvium;
import java.util.*;
import main.resources.teacher.*;
public class zadaca1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        SLLNode<Integer> node = list.getFirst();
        int counter = 0;
        while (node!=null){
            if (node.element.equals(k)){
                counter++;
            }
            node = node.succ;
        }

        if (counter%2!=0){
            node = list.getFirst();
            while (node!=null){
                if (node.element.equals(k)){
                    list.insertAfter(node.element, node);
                    break;
                }
                node = node.succ;
            }
        }

        System.out.println(list);
    }
}
