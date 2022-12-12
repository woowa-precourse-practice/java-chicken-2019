package domain;

import java.util.Objects;

public class Order {
    private final Table table;
    private final Menu menu;
    private Quantity quantity;

    public Order(Table table, Menu menu, Quantity quantity) {
        this.table = table;
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order create(Table table, Menu menu, Quantity quantity) {
        return new Order(table, menu, quantity);
    }

    public boolean hasSameTable(Table table) {
        return this.table.equals(table);
    }

    public void combine(Order order) {
        quantity = quantity.sum(order.quantity);
    }

    public int calculatePayment() {
        return quantity.calculatePayment(menu.getPrice());
    }

    public Quantity getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(table, order.table) && Objects.equals(menu, order.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, menu);
    }

    public boolean isChicken() {
        return menu.isChicken();
    }
}
