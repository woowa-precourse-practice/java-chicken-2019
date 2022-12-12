package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {

    private static final List<Order> orders = new ArrayList<>();

    public static void save(Order order) {
        orders.add(order);
    }

    public static List<Order> get() {
        return Collections.unmodifiableList(orders);
    }
}
