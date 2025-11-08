package zadaciZaVezbanje.Koncepti.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee e = new Employee("Ana", "ID001", 60000);
        Manager m = new Manager("Boris", "ID002", 60000, 0.15); // 15% бонус

        // Наследување + базно однесување
        System.out.println(e.getFullName());           // очекувано: Ana
        System.out.println(e.annualCompensation());    // очекувано: 60000.0

        // Override во детската класа
        System.out.println(m.getFullName());           // очекувано: Boris (Manager)
        System.out.println(m.annualCompensation());    // очекувано: 69000.0

        // Полиморфизам: референца од тип Employee, но објект Manager
        Employee ref = m;
        System.out.println(ref.annualCompensation());  // очекувано: 69000.0

        // Колекции + полиморфизам во циклус
        List<Employee> staff = new ArrayList<>();
        staff.add(e);
        staff.add(m);

        double payroll = Payroll.totalAnnualCompensation(staff);
        System.out.println(payroll);                   // очекувано: 129000.0

        // Дополнителни проверки (не менувај)
        assert e.getClass().getSimpleName().equals("Employee");
        assert m.getClass().getSimpleName().equals("Manager");
        assert ref.getClass().getSimpleName().equals("Manager");
        assert Math.abs(payroll - 129000.0) < 1e-6;

        System.out.println("ALL TESTS PASSED");
    }
}