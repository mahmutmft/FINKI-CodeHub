package main.java.niziListi;

import java.util.Scanner;

import main.resources.teacher.DLL;
import main.resources.teacher.DLLNode;

public class deseta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            int element = scanner.nextInt();
            list.insertLast(element);
        }
        System.out.println(list);

        int m = scanner.nextInt();
        int k = scanner.nextInt();

        DLLNode<Integer> node = list.getFirst();
        DLLNode<Integer> nodeHelper = null;

        while (node != null) {
            if (node.element.equals(m)) {
                nodeHelper = node;
                list.delete(node);
                break;
            }
            node = node.succ;
        }
        int counter = k;
        while (node != null) {
            for (int i = 0; i < k; i++) {
                if (node != null) {
                    node = node.succ;
                    counter--;
                } else {
                    break;
                }
            }
            if (node == null) {
                node = list.getFirst();
                if (counter != 0) {
                    for (int i = 0; i < counter - 1; i++) {
                        node = node.succ;
                    }
                    list.insertAfter(nodeHelper.element, node);
                } else list.insertBefore(nodeHelper.element, node);
            } else {
                list.insertAfter(nodeHelper.element, node);
            }
            break;
        }
        if (nodeHelper == null) {
            System.out.println("Elementot ne postoi vo listata");
        }
        System.out.println(list);
    }
}