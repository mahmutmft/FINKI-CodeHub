package hash;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class ex7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            set.add(word);
        }
        String sentence = scanner.nextLine();
        String[] splitSentence = sentence.split(" "); // Where
        int counter = 0;
        if (sentence.equals(".")) {
            System.out.println("Bravo");
        } else {
            for (String word : splitSentence) {
                String cleanWord = word.replaceAll("[!.,?]", "").toLowerCase();
                if (Character.isUpperCase(word.charAt(0))) {
                    if (!set.contains(cleanWord)) {
                        counter++;
                        System.out.println(cleanWord);
                    }
                } else if (!set.contains(cleanWord)) {
                    counter++;
                    System.out.println(cleanWord);
                }
            }
            if (counter == 0) {
                System.out.println("Bravo");
            }
        }
    }
}
