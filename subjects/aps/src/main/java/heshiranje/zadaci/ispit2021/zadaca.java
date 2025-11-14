package main.java.heshiranje.zadaci.ispit2021;

import java.util.*;

public class zadaca {
    public String testFunction(String input) {
        Scanner scanner = new Scanner(input);
        Map<String, List<Lek>> hash = new HashMap<>();

        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String ime = scanner.next();
            String namena = scanner.next();
            int cena = scanner.nextInt();
            scanner.nextLine();

            Lek lek = new Lek(ime, cena, namena);

            hash.putIfAbsent(namena, new ArrayList<>());
            hash.get(namena).add(lek);

        }

        String searchNamena = scanner.nextLine();
        scanner.close();

        if (!hash.containsKey(searchNamena)) {
            return "Nema lek za baranata namena vo magacin.";
        } else {
            List<Lek> list = hash.get(searchNamena);
            Lek minLek = list.getFirst();
            for (Lek lek : list) {
                if (lek.cena < minLek.cena) {
                    minLek = lek;
                }
            }
            return minLek.name;
        }
    }
}
