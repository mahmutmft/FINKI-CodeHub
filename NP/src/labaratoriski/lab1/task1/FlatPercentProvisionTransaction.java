package labaratoriski.lab1.task1;

import java.util.Objects;

public class FlatPercentProvisionTransaction extends Transaction{


    private final int centsPerDolar;


    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, int centsPerDolar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDolar = centsPerDolar;
    }

    public int getPercent() {
        return centsPerDolar;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return centsPerDolar == that.centsPerDolar;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(centsPerDolar);
    }
}
