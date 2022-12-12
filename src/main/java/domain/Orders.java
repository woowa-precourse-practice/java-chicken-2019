package domain;

import java.util.Set;

public class Orders {

    private static final int MIN_PAYMENT = 0;

    private final Set<Order> orders;

    public Orders(Set<Order> orders) {
        this.orders = orders;
    }

    public static Orders create(Set<Order> orders) {
        return new Orders(orders);
    }

    public int calculateTotalPayment() {
        return orders.stream()
                .map(Order::calculatePayment)
                .reduce(Integer::sum)
                .orElse(MIN_PAYMENT);
    }

    public void add(Order order) {
        orders.add(order);
    }
}
