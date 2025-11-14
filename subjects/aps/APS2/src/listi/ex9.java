package listi;

import java.util.Scanner;

public class ex9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }

        System.out.println(list);

        int m = scanner.nextInt();
        int k = scanner.nextInt();
        boolean check = true;
        DLLNode<Integer> target = list.find(m);
        if (target != null) {
            int value = target.element;
            DLLNode<Integer> current = target;
            for (int i = 0; i < k; i++) {
                if (current.pred != null) {
                    current = current.pred;
                } else {
                    current = list.getLast();
                    check = false;
                }
            }

            list.delete(target);
            if (check){
                list.insertBefore(value, current);
            }else{
                list.insertAfter(value, current);

            }
        }

        System.out.println(list);
    }
}
