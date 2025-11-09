package podatocniStrukturi;

import java.util.*;

public class ex18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        int n = scanner.nextInt();
        int[] array = new int[n];
        boolean flag = true;
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String number = line.substring(line.length()-1);
            if (stack.isEmpty() && line.startsWith("#")){
                stack.push(line);
                array[i] = Integer.parseInt(number);
            }
            else if (line.startsWith("#")){
                if (array[i-1] > Integer.parseInt(number)){
                    flag = false;
                    break;
                }else {
                    array[i] = Integer.parseInt(number);
                    stack.push(line);
                }
            }else if (line.endsWith("#")){
                if (stack.contains(line.substring(0 , line.length()-1))){
                    flag = false;
                    break;
                }else {
                    stack.remove(line.substring(0,line.length()-1));
                }
            }
        }
        System.out.println(flag);
    }
}
