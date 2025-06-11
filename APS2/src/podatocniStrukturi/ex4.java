package podatocniStrukturi;

import java.util.*;

class Students {
    String name;
    boolean nauka;
    boolean fantastika;
    boolean istorija;

    public Students(String name, boolean nauka, boolean fantastika, boolean istorija){
        this.name = name;
        this.nauka = nauka;
        this.fantastika = fantastika;
        this.istorija = istorija;
    }
}

public class ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Queue<Students> nauka = new LinkedList<>();
        Queue<Students> fantastika = new LinkedList<>();
        Queue<Students> istorija = new LinkedList<>();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            boolean naukaP = scanner.nextInt() == 1;
            boolean fantastikaP = scanner.nextInt() == 1;
            boolean istorijaP = scanner.nextInt() == 1;

            Students student = new Students(name,naukaP,fantastikaP,istorijaP);
            if (naukaP){
                nauka.add(student);
            }else if (fantastikaP){
                fantastika.add(student);
            }else {
                istorija.add(student);
            }
            scanner.nextLine();
        }
        while (!nauka.isEmpty() || !fantastika.isEmpty() || !istorija.isEmpty()){
            if (!nauka.isEmpty()){
                Students student = nauka.poll();
                if (student.fantastika) fantastika.add(student);
                else if (student.istorija) istorija.add(student);
                else System.out.println(student.name);
            }else if (!fantastika.isEmpty()){
                Students s = fantastika.poll();
                if (s.istorija) istorija.add(s);
                else System.out.println(s.name);
            }else {
                System.out.println(istorija.poll().name);
            }
        }
    }
}
