package labaratoriski.lab1.task1;

import java.util.Objects;

public class FlatAmountProvisionTransaction extends Transaction{

    private final double flatProvision;


    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, double flatProvision) {
        super(fromId, toId, "FlatPercent", amount);
        this.flatProvision = flatProvision;
    }

    public double getFlatProvision() {
        return flatProvision;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlatAmountProvisionTransaction that = (FlatAmountProvisionTransaction) o;
        return Double.compare(flatProvision, that.flatProvision) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(flatProvision);
    }
}
