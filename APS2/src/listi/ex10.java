package listi;

import java.util.Scanner;

public class ex10 {
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
        DLLNode<Integer> target = list.find(m);
        boolean flag = true;
        if (target != null){
            int value = target.element;
            DLLNode<Integer> current = target;
            for (int i = 0; i < k; i++) {
                if (current.succ != null){
                    current = current.succ;
                }else {
                    current = list.getFirst();
                    flag = false;
                }
            }
            list.delete(target);
            if (flag) {
                list.insertAfter(value, current);
            }else {
                list.insertBefore(value, current);
            }
        }
        System.out.println(list);
    }
}

