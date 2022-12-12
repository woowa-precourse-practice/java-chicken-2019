package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderRepositoryTest {

    @Test
    void 주문을_등록할_수_있다() {
        Table table = TableRepository.findByNumber(1);
        Menu menu = MenuRepository.findByNumber(1);
        Quantity quantity = Quantity.from(1);

        Order order = Order.create(table, menu, quantity);
        OrderRepository.save(order);

        assertThat(OrderRepository.get()).containsExactly(order);
    }
}
