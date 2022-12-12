package dto;

import domain.Order;

public class OrderResponseDto {

    private final String name;
    private final int quantity;
    private final int price;

    private OrderResponseDto(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(
                order.getMenu().getName(),
                order.getQuantity().getAmount(),
                order.getMenu().getPrice()
        );
    }

    @Override
    public String toString() {
        return name + " " + quantity + " " + price;
    }
}
