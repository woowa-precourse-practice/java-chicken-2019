package repository;

import domain.Menu;
import domain.Order;
import domain.Orders;
import domain.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderRepositoryTest {

    Menu menu;
    Order order;
    Orders orders;

    @BeforeEach
    void setUp() {
        menu = MenuRepository.findByNumber(1);
        Quantity quantity = Quantity.from(1);
        order = Order.create(menu, quantity);
        orders = Orders.createEmpty();
    }

    @Test
    void 주문을_등록할_수_있다() {
        orders.add(order);

        assertThat(orders.get()).containsExactly(order);
    }

    @Test
    void 같은_테이블_같은_메뉴_수량만_증가() {
        Order newOrder = Order.create(menu, Quantity.from(2));

        orders.add(order);
        orders.add(newOrder);

        assertThat(orders.get())
                .extracting("quantity")
                .containsExactly(Quantity.from(3));
    }

    @Test
    void 주문_내역이_존재하는지_확인() {
        orders.add(order);

        assertThat(orders.isEmpty()).isFalse();
    }
}
