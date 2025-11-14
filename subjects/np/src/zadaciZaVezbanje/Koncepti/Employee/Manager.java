package zadaciZaVezbanje.Koncepti.Employee;

public class Manager extends Employee{
    private final double bonusRate;

    public Manager(String name, String id, double baseSalary, double bonusRate) {
        super(name, id, baseSalary);
        this.bonusRate = bonusRate;
    }

    @Override
    public String getFullName(){
        return getName() + "(Manager)";
    }

    @Override
    public double annualCompensation(){
        return getBaseSalary() + (1+bonusRate);
    }

    @Override
    public String toString(){
        return String.format("Manager{name=%s, id=%s, baseSalary=%.2f, bonusRate=%.2f}", getName(),getId(),getBaseSalary(),bonusRate);
    }
}
