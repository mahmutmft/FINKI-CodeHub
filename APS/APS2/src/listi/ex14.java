package listi;

import java.util.Scanner;

public class ex14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        DLL<DLL<Integer>> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            DLL<Integer> listDruga = new DLL<>();
            for (int j = 0; j < m; j++) {
                listDruga.insertLast(scanner.nextInt());
            }
            list.insertLast(listDruga);
        }
        DLLNode<DLL<Integer>> node = list.getFirst();
        int sum = 1;
        for (int i = 0; i < n; i++) {
            int sumaLista = 0;
            DLLNode<Integer> nodeHelper = node.element.getFirst();
            for (int j = 0; j < m; j++) {
                sumaLista+=nodeHelper.element;
                nodeHelper = nodeHelper.succ;
            }
            sum*=sumaLista;
            node = node.succ;
        }
        System.out.println(sum);
    }
}
