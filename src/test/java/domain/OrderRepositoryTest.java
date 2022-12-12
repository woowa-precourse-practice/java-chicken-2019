package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderRepositoryTest {

    Order order;
    Table table;
    Menu menu;

    @BeforeEach
    void setUp() {
        table = TableRepository.findByNumber(1);
        menu = MenuRepository.findByNumber(1);
        Quantity quantity = Quantity.from(1);

        order = Order.create(table, menu, quantity);
    }

    @Test
    void 주문을_등록할_수_있다() {
        OrderRepository.save(order);

        assertThat(OrderRepository.get()).containsExactly(order);
    }

    @Test
    void 같은_테이블_같은_메뉴_수량만_증가() {
        Order newOrder = Order.create(table, menu, Quantity.from(2));

        OrderRepository.save(order);
        OrderRepository.save(newOrder);

        assertThat(OrderRepository.get())
                .extracting("quantity")
                .containsExactly(Quantity.from(3));
    }

    @Test
    void 해당_테이블에_주문_내역이_존재하는지_확인() {
        OrderRepository.save(order);

        assertThat(OrderRepository.hasTable(table)).isTrue();
    }

    @AfterEach
    void clear() {
        OrderRepository.clearByTable(table);
    }
}
