package listi;

import java.util.Scanner;

public class ex13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        DLLNode<Integer> node = list.getFirst().succ;
        int bigger = 0;
        for (int i = 0; i < list.getSize()-2; i++) {
            DLLNode<Integer> left = node.pred;
            DLLNode<Integer> right = node.succ;

            int sumLeft = 0, countLeft = 0;
            int sumRight = 0, countRight = 0;

            while (left!=null){
                sumLeft += left.element;
                countLeft++;
                left = left.pred;
            }

            while (right != null){
                sumRight += right.element;
                countRight++;
                right = right.succ;
            }

            double averageLeft = (double) sumLeft / countLeft;
            double averageRight = (double) sumRight / countRight;

            if (averageLeft>averageRight){
                bigger++;
            }
        }
        System.out.println(bigger);
    }
}
