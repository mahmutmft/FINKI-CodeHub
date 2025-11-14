Вежба 1 — Employee → Manager
Што ќе вежбаме

extends, super (наследување)

@Override (полиморфизам)

мала употреба на ArrayList

Твоја задача (класи што треба да ги направиш)

Employee (основна класа)

поља: String name, String id, double baseSalary

конструктор: (String name, String id, double baseSalary)

методи:

String getFullName() → враќа само name

double annualCompensation() → по дифолт враќа baseSalary

String toString() → "Employee{name='...', id='...', baseSalary=...}"

Manager (наследува Employee)

дополнително поље: double bonusRate (пример 0.15 за 15%)

конструктор: (String name, String id, double baseSalary, double bonusRate) → користи super(...)

override:

getFullName() → "name (Manager)"

annualCompensation() → baseSalary * (1 + bonusRate)

toString() → "Manager{name='...', id='...', baseSalary=..., bonusRate=...}"

Payroll (utility)

static double totalAnnualCompensation(List<Employee> staff) → сума на annualCompensation() за сите