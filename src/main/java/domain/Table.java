package domain;

import java.util.Objects;

public class Table {

    private static final String NO_ORDERS_ERROR = "주문 내역이 존재하지 않습니다.";

    private final int number;
    private Orders orders;

    public Table(final int number) {
        this.number = number;
        orders = Orders.createEmpty();
    }

    public boolean hasSameNumber(int number) {
        return this.number == number;
    }

    public int getNumber() {
        return number;
    }

    public Orders getOrders() {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(NO_ORDERS_ERROR);
        }
        return orders;
    }

    public void clearOrders() {
        orders = orders.clear();
    }

    public void order(Menu menu, Quantity quantity) {
        Order order = Order.create(menu, quantity);
        orders.add(order);
    }

    public boolean hasNoOrders() {
        return orders.isEmpty();
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return number == table.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
