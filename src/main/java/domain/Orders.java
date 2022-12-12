package domain;

import java.util.Set;

public class Orders {

    private static final int MIN_PAYMENT = 0;
    private static final int NO_QUANTITY = 0;

    private final Set<Order> orders;

    public Orders(Set<Order> orders) {
        this.orders = orders;
    }

    public static Orders create(Set<Order> orders) {
        return new Orders(orders);
    }

    public int calculateTotalPayment() {
        int totalPaymentWithoutDiscount = calculateTotalPaymentWithoutDiscount();

        return applyDiscount(totalPaymentWithoutDiscount);
    }

    private int calculateTotalPaymentWithoutDiscount() {
        return orders.stream()
                .map(Order::calculatePayment)
                .reduce(Integer::sum)
                .orElse(MIN_PAYMENT);
    }

    private int applyDiscount(int payment) {
        Quantity chickenCount = countChicken();

        return payment - chickenCount.calculateDiscountAmount();
    }

    private Quantity countChicken() {
        return orders.stream()
                .filter(Order::isChicken)
                .map(Order::getQuantity)
                .reduce(Quantity::sum)
                .orElse(Quantity.from(NO_QUANTITY));
    }

    public void add(Order order) {
        orders.add(order);
    }
}
