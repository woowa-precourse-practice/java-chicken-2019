package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PaymentMethodTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "3", "-1", "s"})
    void 결제_수단_유효성_검증(String command) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> PaymentMethod.from(command));
    }
}
