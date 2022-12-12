package repository;

import domain.Order;
import domain.Table;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderRepository {

    private static final Set<Order> orders = new HashSet<>();

    public static void save(Order order) {
        orders.add(update(order));
    }

    private static Order update(Order order) {
        return orders.stream()
                .filter(existingOrder -> existingOrder.equals(order))
                .peek(existingOrder -> existingOrder.combine(order))
                .findFirst()
                .orElse(order);
    }

    public static Set<Order> get() {
        return Collections.unmodifiableSet(orders);
    }

    public static void clearByTable(Table table) {
        orders.removeIf(order -> order.hasSameTable(table));
    }

    public static boolean hasTable(Table table) {
        return orders.stream()
                .anyMatch(order -> order.hasSameTable(table));
    }

    public static Set<Order> findByTable(Table table) {
        return orders.stream()
                .filter(order -> order.hasSameTable(table))
                .collect(Collectors.toSet());
    }
}
