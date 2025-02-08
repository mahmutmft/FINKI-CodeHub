package main.java.ednoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Student> priloziDoc = new LinkedList<>();
        Queue<Student> zemiIndeks = new LinkedList<>();
        Queue<Student> zemiDoc = new LinkedList<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            boolean prilozi = scanner.nextInt() == 1;
            boolean zemiIndx = scanner.nextInt() == 1;
            boolean zemidoc = scanner.nextInt() == 1;
            Student s = new Student(name, prilozi, zemiIndx, zemidoc);
            scanner.nextLine();
            if (prilozi) priloziDoc.add(s);
            else if (zemiIndx) zemiIndeks.add(s);
            else zemiDoc.add(s);

        }
        while (!zemiIndeks.isEmpty() || !priloziDoc.isEmpty() || !zemiDoc.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                Student student = priloziDoc.poll();
                if (student != null) {
                    if (student.fiction) zemiIndeks.add(student);
                    else if (student.history) zemiDoc.add(student);
                    else System.out.println(student.name);
                } else break;
            }
            for (int i = 0; i < 3; i++) {
                Student studentce = zemiIndeks.poll();
                if (studentce != null) {
                    if (studentce.history) zemiDoc.add(studentce);
                    else System.out.println(studentce.name);
                }
            }
            if (!zemiDoc.isEmpty()) {
                System.out.println(zemiDoc.poll().name);
            }

        }
    }
}
