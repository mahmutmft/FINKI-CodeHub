package listi;

import java.util.Scanner;

public class ex11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        System.out.println(list);
        DLLNode<Integer> current = list.getLast();
        for (int i = 0; i < k; i++) {
            list.insertFirst(current.element);
            list.delete(current);
            current = current.pred;
        }
        System.out.println(list);
    }
}
