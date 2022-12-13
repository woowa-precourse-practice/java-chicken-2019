package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Orders {

    private static final int MIN_PAYMENT = 0;
    private static final int NO_QUANTITY = 0;

    private final Set<Order> orders;

    public Orders(Set<Order> orders) {
        this.orders = orders;
    }

    public static Orders createEmpty() {
        return new Orders(new HashSet<>());
    }

    public int calculatePayment(PaymentMethod paymentMethod) {
        DiscountPolicy discountPolicy = DiscountPolicy.from(paymentMethod);
        int payment = addUpPayments();
        Quantity chickenCount = countChicken();

        return discountPolicy.apply(payment, chickenCount);
    }

    private int addUpPayments() {
        return orders.stream()
                .map(Order::calculatePayment)
                .reduce(Integer::sum)
                .orElse(MIN_PAYMENT);
    }

    public void add(Order order) {
        Order updated = update(order);
        orders.add(updated);
    }

    private Order update(Order order) {
        return orders.stream()
                .filter(existingOrder -> existingOrder.equals(order))
                .peek(existingOrder -> existingOrder.combine(order))
                .findFirst()
                .orElse(order);
    }

    public Set<Order> get() {
        return Collections.unmodifiableSet(orders);
    }

    private Quantity countChicken() {
        return orders.stream()
                .filter(Order::isChicken)
                .map(Order::getQuantity)
                .reduce(Quantity::sum)
                .orElse(Quantity.from(NO_QUANTITY));
    }

    public Orders clear() {
        return Orders.createEmpty();
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }
}
