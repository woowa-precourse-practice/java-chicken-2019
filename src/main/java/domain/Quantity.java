package domain;

import java.util.Objects;

public class Quantity {

    private static final String EXCEED_QUANTITY_UPPER_BOUND = "수량은 99개가 최대입니다.";
    private static final String INVALID_NEGATIVE_QUANTITY = "수량은 음수가 될 수 없습니다";
    private static final int QUANTITY_UPPER_BOUND = 99;
    private static final int QUANTITY_LOWER_BOUND = 0;

    private final int amount;

    public Quantity(int amount) {
        this.amount = amount;
    }

    public static Quantity from(int amount) {
        validate(amount);
        return new Quantity(amount);
    }

    private static void validate(int amount) {
        if (amount > QUANTITY_UPPER_BOUND) {
            throw new IllegalArgumentException(EXCEED_QUANTITY_UPPER_BOUND);
        }
        if (amount < QUANTITY_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_NEGATIVE_QUANTITY);
        }
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
