package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuantityTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 100})
    void 주문_수량_유효성_검증(int amount) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Quantity.from(amount));
    }
}
