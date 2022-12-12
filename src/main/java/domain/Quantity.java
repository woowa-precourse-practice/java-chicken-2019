package domain;

import java.util.Objects;

public class Quantity {

    private final int amount;

    public Quantity(int amount) {
        this.amount = amount;
    }

    public static Quantity from(int amount) {
        return new Quantity(amount);
    }

    public Quantity sum(Quantity quantity) {
        return new Quantity(this.amount + quantity.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return amount == quantity.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
