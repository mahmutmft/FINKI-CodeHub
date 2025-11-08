package zadaciZaVezbanje.Koncepti.Employee;

public class Employee {
    private String name;
    private String id;
    private double baseSalary;

    public Employee(String name, String id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    public String getFullName() {
        return name;
    }

    public double annualCompensation(){
        return baseSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", baseSalary=" + baseSalary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}
