package main.java.niziListi;

import java.util.*;

import main.resources.teacher.*;

public class peta {
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
        int counter = 0;
        while (node != null){
            counter++;
            SLLNode<String> nodeNext = node.succ;
            if (node.element.length() == L){
                String el = node.element;
                list.delete(node);
                list.insertLast(el);
            }
            if (counter == n) break;
            node = nodeNext;
        }
        System.out.println(list);
    }
}
