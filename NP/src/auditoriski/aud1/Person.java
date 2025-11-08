package auditoriski.aud1;

import java.util.Objects;

public class Person {

    private String name;
    private String surname;
    private int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person("Mahmut", "Miftaroski", 20);
        Person person3 = new Person("Mahmut", "Miftaroski", 20);

        if(person2 == person3){
            System.out.println(true);
        }else{
            System.out.println(false);
        }

        if (person2.equals(person3)){
            System.out.println(true);
        }else{
            System.out.println(false);
        }

        System.out.println(person2);

    }
}

