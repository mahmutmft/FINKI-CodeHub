package main.java.niziListi;

import main.resources.teacher.*;

import java.util.Scanner;

public class zadaca13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Double> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextDouble());
        }
        DLLNode<Double> node = null;
        DLLNode<Double> nodeHelper = list.getFirst();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            node = nodeHelper;
            double sumRight = 0;
            double sumLeft = 0;
            int counterHelper1 = 0;
            while (node!=null){
                sumLeft += node.element;
                node = node.pred;
                counterHelper1++;
            }
            int counterHelper2 = 0;
            node = nodeHelper.succ;
            while (node!=null){
                sumRight+=node.element;
                node = node.succ;
                counterHelper2++;
            }
            double avgLeft = (counterHelper1 == 0) ? 0 : sumLeft / counterHelper1;
            double avgRight = (counterHelper2 == 0) ? 0 : sumRight / counterHelper2;
            if (nodeHelper.succ == null) break;
            if (avgLeft>avgRight){
                counter++;
            }
            nodeHelper = nodeHelper.succ;
        }
        counter = counter-1;
        if (counter<0){
            counter = 0;
            System.out.println(counter);
        }else System.out.println(counter);
    }
}
