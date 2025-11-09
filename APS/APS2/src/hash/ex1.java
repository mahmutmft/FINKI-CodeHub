package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ex1 {
    static class Vraboteni {
        String name;
        int age;

        public Vraboteni(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    static class Proekti {
        int time;
        int saleryHour;

        public Proekti(int time, int saleryHour) {
            this.time = time;
            this.saleryHour = saleryHour;
        }

        public int salerSum() {
            return time * saleryHour;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Vraboteni, Proekti> table = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int age = scanner.nextInt();
            int time = scanner.nextInt();
            int rate = scanner.nextInt();
            scanner.nextLine();
            Vraboteni person = new Vraboteni(name, age);
            Proekti proekt = new Proekti(time, rate);
            if (!table.containsKey(person)) {
                table.put(person, proekt);
            } else {
                Proekti exist = table.get(person);
                if (proekt.salerSum() > exist.salerSum()) {
                    table.put(person, proekt);
                }
            }
        }
        Object[] outputTable = new Object[10];
        for (Map.Entry<Vraboteni, Proekti> entry : table.entrySet()) {
            int index = entry.getKey().hashCode();
            outputTable[index] = "<<" + entry.getKey() + ">,<" + entry.getValue() + ">>";
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":");
            if (outputTable[i] != null){
                System.out.println(outputTable[i]);
            }
            System.out.println();
        }
    }
}
