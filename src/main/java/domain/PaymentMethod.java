package domain;

public enum PaymentMethod {
    CASH, CARD;

    public boolean isCash() {
        return this == CASH;
    }
}
