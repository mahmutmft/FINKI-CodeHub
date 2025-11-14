package auditoriski.aud2;

import java.math.BigDecimal;

public class BIgComplex {
    private BigDecimal realPart;
    private BigDecimal imaginaryPart;

    public BIgComplex() {
    }

    public BIgComplex(BigDecimal realPart, BigDecimal imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public BIgComplex add(BIgComplex complex){
        return new BIgComplex(this.realPart.add(complex.realPart), this.imaginaryPart.add(complex.imaginaryPart));
    }

    @Override
    public String toString() {
        return "BIgComplex{" +
                "realPart=" + realPart +
                ", imaginaryPart=" + imaginaryPart +
                '}';
    }
}
