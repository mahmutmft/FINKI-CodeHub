package listi;

import java.util.Scanner;

public class еx14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int L = scanner.nextInt();

        SLLNode<Integer> current = list.getFirst();
        int counter = 0;
        while (current!=null){
            if (current.element == L){
                counter++;
            }
            current = current.succ;
        }
        if (counter%2==0){
            System.out.println(list);
        }else {
            SLLNode<Integer> current1 = list.getFirst();
            while (current1!=null){
                if (current1.element == L){
                    list.insertBefore(L, current1);
                    break;
                }
                current1 = current1.succ;
            }
            System.out.println(list);
        }
    }
}
