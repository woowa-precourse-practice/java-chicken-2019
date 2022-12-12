package domain;

public class Quantity {

    private final int amount;

    public Quantity(int amount) {
        this.amount = amount;
    }

    public static Quantity from(int amount) {
        return new Quantity(amount);
    }
}
