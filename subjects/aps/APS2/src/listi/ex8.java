package listi;

import java.util.Scanner;

public class ex8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        System.out.println(list);
        int L = scanner.nextInt();
        for (int i = 0; i < L; i++) {
            DLLNode<Integer> node = list.getFirst();
            list.insertLast(node.element);
            list.delete(node);
        }
        System.out.println(list);
    }
}
