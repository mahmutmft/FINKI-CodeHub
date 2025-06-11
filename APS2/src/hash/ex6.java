package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, String> hash = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String kum = scanner.next();
            String mkd = scanner.next();
            scanner.nextLine();
            hash.put(kum, mkd);
        }
        String sentence = scanner.nextLine();
        String[] splitSentence = sentence.split(" ");
        for (int i = 0; i < splitSentence.length; i++) {
            String word = splitSentence[i];  // rakiju.
            String cleanWord = word.replaceAll("[!.,?]", ""); // rakiju
            String znak = word.replaceAll("[a-zA-Z’]", ""); // .
            boolean firstUpper = Character.isUpperCase(cleanWord.charAt(0)); // false
            if (firstUpper) {
                String changedWord = Character.toLowerCase(cleanWord.charAt(0)) + cleanWord.substring(1);
                if (hash.containsKey(changedWord)) {
                    String finalWord = hash.get(changedWord); // jade
                    String finalFinalWord = finalWord.replace("’", "");
                    splitSentence[i] = Character.toUpperCase(finalFinalWord.charAt(0)) + finalWord.substring(1) + znak;
                }
            } else {
                if (hash.containsKey(cleanWord)) { // rakiju rakija
                    String finalWord = hash.get(cleanWord);
                    String finalFinalWord = finalWord.replace("’", "");
                    splitSentence[i] = finalFinalWord + znak; // rakija.
                }
            }
        }
        for (String word : splitSentence) {
            System.out.print(word + " ");
        }
    }
}
