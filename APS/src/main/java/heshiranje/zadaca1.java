package main.java.heshiranje;
import main.resources.teacher.*;


import java.util.Scanner;
import java.util.Objects;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + age + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return age * name.charAt(0);
    }
}

class Project {
    int time;
    int rate;

    public Project(int time, int rate) {
        this.time = time;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "<" + time + ", " + rate + ">";
    }

    public int salarySum() {
        return time * rate;
    }
}

public class zadaca1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<Person, Project> table = new CBHT<>(10);
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            int age = scanner.nextInt();
            int time = scanner.nextInt();
            int rate = scanner.nextInt();

            Person person = new Person(name, age);
            Project project = new Project(time,rate);

            SLLNode<MapEntry<Person,Project>> node = table.search(person);

            if (node == null){
                table.insert(person, project);
            }
            else{
                Project existingProject = node.element.value;
                if (project.salarySum()>existingProject.salarySum()){
                    table.insert(person, project);
                }
            }

        }
        System.out.println(table);
    }
}
