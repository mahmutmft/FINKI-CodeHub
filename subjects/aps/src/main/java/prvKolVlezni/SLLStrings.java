package main.java.prvKolVlezni;

import main.resources.teacher.*;

import java.util.*;

public class SLLStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<String> list = new SLL<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextLine());
        }
        System.out.println(list);
        SLLNode<String> node = list.getFirst();
        int counter = 0;
        while (node != null){
            if (Character.isLowerCase(node.element.charAt(0))){
                list.insertLast(node.element);
                list.delete(node);
            }
            counter++;
            if (counter == n) break;
            node = node.succ;
        }
        System.out.println(list);
    }
}
