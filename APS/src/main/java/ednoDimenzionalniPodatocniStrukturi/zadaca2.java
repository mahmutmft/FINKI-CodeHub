package main.java.ednoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Queue<Student> scienceQ = new LinkedList<>();
        Queue<Student> fictionQ = new LinkedList<>();
        Queue<Student> historyQ = new LinkedList<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            boolean science = scanner.nextInt() == 1;
            boolean fiction = scanner.nextInt() == 1;
            boolean history = scanner.nextInt() == 1;
            Student s = new Student(name,science,fiction,history);
            if (science){
                scienceQ.offer(s);
            } else if (fiction) {
                fictionQ.offer(s);
            }else {
                historyQ.offer(s);
            }
            scanner.nextLine();
        }
        while (!scienceQ.isEmpty() || !fictionQ.isEmpty() || !historyQ.isEmpty()){
            if (!scienceQ.isEmpty()){
                Student s = scienceQ.poll();
                if (s.fiction) fictionQ.add(s);
                else if (s.history) historyQ.add(s);
                else System.out.println(s.name);
            } else if (!fictionQ.isEmpty()) {
                Student s = fictionQ.poll();
                if (s.history) historyQ.offer(s);
                else System.out.println(s.name);
            } else System.out.println(historyQ.poll().name);
        }
    }
}