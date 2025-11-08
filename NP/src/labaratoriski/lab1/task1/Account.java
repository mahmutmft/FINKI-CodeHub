package labaratoriski.lab1.task1;

import java.util.Objects;
import java.util.Random;

class Account {

    private final long id;
    private final String name;
    private double balance;

    private static final Random random = new Random();
    public Account(String name, double balance) {
        this.id = genId();
        this.name = name;
        this.balance = balance;
    }

    private long genId() {
        long id = Math.abs(random.nextLong());
        return id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name:%s%nBalance:%.2f$%n", name, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(balance, account.balance) == 0 && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance);
    }
}


