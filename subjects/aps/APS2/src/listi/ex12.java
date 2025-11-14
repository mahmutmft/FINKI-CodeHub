package listi;

import java.util.Scanner;

public class ex12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        System.out.println(list);
        DLLNode<Integer> current = list.getFirst();
        for (int i = 0; i < k; i++) {
            list.insertLast(current.element);
            list.delete(current);
            current = current.succ;
        }
        System.out.println(list);
    }
}
