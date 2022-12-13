package domain;

import java.util.Objects;

public class Order {
    private final Menu menu;
    private Quantity quantity;

    public Order(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order create(Menu menu, Quantity quantity) {
        return new Order(menu, quantity);
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

    public Menu getMenu() {
        return menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(menu, order.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }

    public boolean isChicken() {
        return menu.isChicken();
    }
}
