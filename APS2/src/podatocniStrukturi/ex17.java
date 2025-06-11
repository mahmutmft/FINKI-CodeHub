package podatocniStrukturi;

import java.util.*;

public class ex17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> deque = new ArrayDeque<>();
        boolean flag = true;
        while (true){
            String s = scanner.nextLine();
            if (s.equals("x")) break;
            if (!s.startsWith("end")){
                deque.push(s);
            }else {
                if (!deque.contains(s.substring(3))){
                    flag = false;
                    break;
                }else{
                    deque.remove(s.substring(3));
                }
            }
        }
        if(!deque.isEmpty()) flag = false;
        if(flag) System.out.println("Valid");
        else System.out.println("Invalid");
    }
}
