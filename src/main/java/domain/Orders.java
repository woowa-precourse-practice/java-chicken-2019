package domain;

import java.util.Collections;
import java.util.Set;

public class Orders {

    private static final int MIN_PAYMENT = 0;
    private static final int NO_QUANTITY = 0;
    private static final double DISCOUNTED = 0.95;

    private final Set<Order> orders;

    public Orders(Set<Order> orders) {
        this.orders = orders;
    }

    public static Orders create(Set<Order> orders) {
        return new Orders(orders);
    }

    public int calculatePayment(PaymentMethod paymentMethod) {
        int payment = addUpPayments();

        return applyDiscount(payment, paymentMethod);
    }

    private int addUpPayments() {
        return orders.stream()
                .map(Order::calculatePayment)
                .reduce(Integer::sum)
                .orElse(MIN_PAYMENT);
    }

    private int applyDiscount(int payment, PaymentMethod paymentMethod) {
        payment = discountByChickenCount(payment);

        return discountByPaymentMethod(payment, paymentMethod);
    }

    private static int discountByPaymentMethod(int payment, PaymentMethod paymentMethod) {
        if (paymentMethod.isCash()) {
            payment *= DISCOUNTED;
        }
        return payment;
    }

    private int discountByChickenCount(int payment) {
        Quantity chickenCount = countChicken();
        payment -= chickenCount.calculateDiscountAmount();

        return payment;
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

    public Set<Order> get() {
        return Collections.unmodifiableSet(orders);
    }
}
