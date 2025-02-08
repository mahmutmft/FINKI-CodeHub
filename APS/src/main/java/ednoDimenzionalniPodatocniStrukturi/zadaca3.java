package main.java.ednoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Student> scienceQ = new LinkedList<>();
        Queue<Student> fictionQ = new LinkedList<>();
        Queue<Student> historyQ = new LinkedList<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n ; i++) {
            String name = scanner.nextLine();
            boolean science = scanner.nextInt() == 1;
            boolean fiction = scanner.nextInt() == 1;
            boolean history = scanner.nextInt() == 1;
            Student s = new Student(name,science,fiction,history);
            scanner.nextLine();
            if (science) scienceQ.add(s);
            else if (fiction) fictionQ.add(s);
            else historyQ.add(s);

        }
        while (!historyQ.isEmpty() || !fictionQ.isEmpty() || !scienceQ.isEmpty()){
            for (int i = 0; i < 2; i++) {
                Student student = scienceQ.poll();
                if (student !=null){
                    if (student.fiction) fictionQ.add(student);
                    else if (student.history) historyQ.add(student);
                    else System.out.println(student.name);
                }else break;
            }
            Student studentce = fictionQ.poll();
            if (studentce!=null){
                if (studentce.history) historyQ.add(studentce);
                else System.out.println(studentce.name);
            }
            for (int i = 0; i < 2; i++) {
                if (!historyQ.isEmpty()) {
                    System.out.println(historyQ.poll().name);
                }
            }
        }
    }
}
