package domain;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import repository.MenuRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrdersTest {

    Orders orders = Orders.createEmpty();

    @BeforeEach
    void setUp() {
        List<Order> orderList = Lists.newArrayList(
                Order.create(MenuRepository.findByNumber(1), Quantity.from(3)),
                Order.create(MenuRepository.findByNumber(2), Quantity.from(3)),
                Order.create(MenuRepository.findByNumber(3), Quantity.from(3)),
                Order.create(MenuRepository.findByNumber(21), Quantity.from(10))
        );
        orderList.forEach(orders::add);
    }

    @Test
    void 주문_총액_계산() {
        int totalPayment = orders.calculatePayment(PaymentMethod.CARD);

        assertThat(totalPayment).isEqualTo(154_000);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:160000", "11:310000", "21: 460000"}, delimiter = ':')
    void 치킨_갯수에_따른_할인율_계산(int amount, int payment) {
        orders.add(
                Order.create(MenuRepository.findByNumber(4), Quantity.from(amount))
        );

        int totalPayment = orders.calculatePayment(PaymentMethod.CARD);

        assertThat(totalPayment).isEqualTo(payment);
    }

    @Test
    void 결제_수단에_따른_할인율_계산() {
        int totalPayment = orders.calculatePayment(PaymentMethod.CASH);

        assertThat(totalPayment).isEqualTo(146_300);
    }
}
