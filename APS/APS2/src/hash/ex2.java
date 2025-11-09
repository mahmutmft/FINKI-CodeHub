package hash;

import java.util.*;

public class ex2 {
    static class Person {
        String Ime;
        String Prezime;
        int budzhet;
        String ip_adresa;
        String vreme;
        String grad;
        int cena;

        public Person(String ime, String prezime, int budzhet, String ip_adresa, String vreme, String grad, int cena) {
            Ime = ime;
            Prezime = prezime;
            this.budzhet = budzhet;
            this.ip_adresa = ip_adresa;
            this.vreme = vreme;
            this.grad = grad;
            this.cena = cena;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, ArrayList<Person>> table = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String Ime = scanner.next();
            String Prezime = scanner.next();
            int budzhet = scanner.nextInt();
            String ip_adresa = scanner.next();
            String vreme = scanner.next();
            String grad = scanner.next();
            int cena = scanner.nextInt();
            Person person = new Person(Ime, Prezime, budzhet, ip_adresa, vreme, grad, cena);
            table.computeIfAbsent(grad, k -> new ArrayList<>()).add(person);
        }
        scanner.nextLine();
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            String Ime = scanner.next();
            String Prezime = scanner.next();
            int budzhet = scanner.nextInt();
            String ip_adresa = scanner.next();
            String vreme = scanner.next();
            String grad = scanner.next();
            int cena = scanner.nextInt();
            Person testCase = new Person(Ime, Prezime, budzhet, ip_adresa, vreme, grad, cena);
            System.out.println("City:" + testCase.grad + " has the following number of customers:");
            List<Person> list = table.get(grad);
            if (!list.isEmpty()) {
                int countBilets = 0;
                Person topSpender = null;
                for (Person person : list) {
                    if (person.budzhet >= person.cena) {
                        countBilets++;
                        if (topSpender == null || person.cena> topSpender.cena) {
                            topSpender = person;
                        }
                    }
                }
                System.out.println(countBilets);
                if (topSpender != null) {
                    System.out.println("The user who spent the most purchasing for that city is:");
                    System.out.println(topSpender.Ime + " " + topSpender.Prezime + " with salary " + topSpender.budzhet + " from address " + topSpender.ip_adresa + " who spent " + topSpender.cena);
                }
                System.out.println();
            }
        }
    }
}
