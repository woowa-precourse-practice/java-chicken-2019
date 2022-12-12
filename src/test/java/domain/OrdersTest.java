package domain;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrdersTest {

    Orders orders;
    Table table;

    @BeforeEach
    void setUp() {
        table = TableRepository.findByNumber(1);
        ArrayList<Order> orderList = Lists.newArrayList(
                Order.create(table, MenuRepository.findByNumber(1), Quantity.from(3)),
                Order.create(table, MenuRepository.findByNumber(2), Quantity.from(3)),
                Order.create(table, MenuRepository.findByNumber(3), Quantity.from(3)),
                Order.create(table, MenuRepository.findByNumber(21), Quantity.from(10))
        );
        orders = Orders.create(Sets.newHashSet(orderList));
    }

    @Test
    void 주문_총액_계산() {
        int totalPayment = orders.calculateTotalPayment();

        assertThat(totalPayment).isEqualTo(154_000);
    }
}
