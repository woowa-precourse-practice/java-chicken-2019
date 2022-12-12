package dto;

import domain.Orders;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersResponseDto {

    private final List<OrderResponseDto> responses;

    private OrdersResponseDto(List<OrderResponseDto> responses) {
        this.responses = responses;
    }

    public static OrdersResponseDto from(Orders orders) {
        return new OrdersResponseDto(convertToDto(orders));
    }

    private static List<OrderResponseDto> convertToDto(Orders orders) {
        return orders.get().stream()
                .map(OrderResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<OrderResponseDto> get() {
        return Collections.unmodifiableList(responses);
    }
}
