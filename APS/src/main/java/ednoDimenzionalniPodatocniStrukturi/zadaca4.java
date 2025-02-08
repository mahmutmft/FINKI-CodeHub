package main.java.ednoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Student> davanjeDoc = new LinkedList<>();
        Queue<Student> priloziDoc = new LinkedList<>();
        Queue<Student> zemiDoc = new LinkedList<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            boolean davanj = scanner.nextInt() == 1;
            boolean prilozi = scanner.nextInt() == 1;
            boolean zemidoc = scanner.nextInt() == 1;
            scanner.nextLine();
            Student student = new Student(name, davanj, prilozi, zemidoc);
            if (davanj) {
                davanjeDoc.add(student);
            } else if (prilozi) {
                priloziDoc.add(student);
            } else {
                zemiDoc.add(student);
            }
        }
        while (!davanjeDoc.isEmpty() || !priloziDoc.isEmpty() || !zemiDoc.isEmpty()) {
            Student s = davanjeDoc.poll();
            if (s != null) {
                if (s.fiction) priloziDoc.add(s);
                else if (s.history) zemiDoc.add(s);
                else System.out.println(s.name);
            }
            Student s2 = priloziDoc.poll();
            if (s2 != null) {
                if (s2.history) zemiDoc.add(s2);
                else System.out.println(s2.name);
            }
            Student s3 = zemiDoc.poll();
            if (s3 != null) {
                System.out.println(s3.name);
            }
        }
    }
}
