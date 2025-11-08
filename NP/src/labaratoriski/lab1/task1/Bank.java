package labaratoriski.lab1.task1;

import java.util.Arrays;
import java.util.Objects;

public class Bank {

    private final String name;
    private final Account[] accounts;
    protected double totalTransfers;          // само сума на amount
    protected double totalProvision;


    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = Arrays.copyOf(accounts, accounts.length);
        this.totalProvision = totalTransfers;
        this.totalTransfers = totalProvision;
    }

    private Account findById(long id) {
        for (Account a : accounts) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Account[] getAccounts() {
        return Arrays.copyOf(accounts, accounts.length);
    }


    public boolean makeTransaction(Transaction t) {
        Account from = findById(t.getFromId());
        Account to = findById(t.getToId());

        if (from == null || to == null) return false;

        double provision;

        if (t instanceof FlatAmountProvisionTransaction) {
            provision = ((FlatAmountProvisionTransaction) t).getFlatProvision();
        } else if (t instanceof FlatPercentProvisionTransaction) {
            int p = ((FlatPercentProvisionTransaction) t).getPercent();
            provision = t.getAmount() * p / 100.0;
        } else {
            provision = 0.0;
        }

        double totalDebit = t.getAmount() + provision;
        if (from.getBalance() < totalDebit){
            return false;
        }
        from.setBalance(from.getBalance() - totalDebit);
        to.setBalance(to.getBalance() + t.getAmount());

        totalTransfers += t.getAmount();
        totalProvision += provision;

        return true;
    }

    public double totalProvision() {
        return totalTransfers;
    }

    public double totalTransfers() {
        return totalProvision;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name:%s%n%n", name));
        for (Account acc : accounts){
           stringBuilder.append(acc.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(totalTransfers, bank.totalTransfers) == 0 && Double.compare(totalProvision, bank.totalProvision) == 0 && Objects.equals(name, bank.name) && Objects.deepEquals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), totalTransfers, totalProvision);
    }
}
