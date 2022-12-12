package domain;

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
}
