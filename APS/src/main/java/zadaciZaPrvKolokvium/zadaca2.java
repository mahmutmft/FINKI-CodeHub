package main.java.zadaciZaPrvKolokvium;

import main.resources.teacher.DLL;
import main.resources.teacher.DLLNode;

import java.util.Scanner;

public class zadaca2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        DLL<DLL<Integer>> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            DLL<Integer> tmp = new DLL<Integer>();
            for (int j = 0; j < m; j++) {
                tmp.insertLast(scanner.nextInt());
            }
            list.insertLast(tmp);
        }
        DLLNode<DLL<Integer>> node = list.getFirst();
        long multiply = 1;
        while (node!=null){
            DLLNode<Integer> nodeHelper = node.element.getFirst();
            int sum = 0;
            while (nodeHelper!=null){
                sum += nodeHelper.element;
                nodeHelper = nodeHelper.succ;
            }
            multiply *=sum;
            node = node.succ;
        }
        System.out.println(multiply);
    }
}
