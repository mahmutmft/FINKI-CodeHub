package zadaciZaVezbanje.Koncepti.Employee;

import java.util.List;

public class Payroll {
    public static double totalAnnualCompensation(List<Employee> staff){
        double totalMoney = 0;
        for (Employee e : staff){
            totalMoney += e.annualCompensation();
        }
        return totalMoney;
    }
}
