package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Rodendeni2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, String> map = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String date = scanner.next();
            map.putIfAbsent(name, date);
            scanner.nextLine();
        }
        int month = scanner.nextInt();
        for (Map.Entry<String, String> entry : map.entrySet()){
            String name = entry.getKey();
            String date = entry.getValue();
            String[] parts = date.split("\\.");
            int monthGet = Integer.parseInt(parts[1]);
            if (monthGet == month){
                System.out.println(name);
            }

        }
    }
}
