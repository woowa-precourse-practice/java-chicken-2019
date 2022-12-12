package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TableRepositoryTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 8})
    void 테이블_정상_등록_테스트(int number) {
        assertThatNoException()
                .isThrownBy(() -> TableRepository.findByNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 7, 9, 10, -11})
    void 테이블_등록_예외_테스트(int number) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> TableRepository.findByNumber(number));
    }
}
