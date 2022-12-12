package repository;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MenuRepositoryTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 21, 22})
    void 메뉴_번호로_메뉴를_찾을_수_있다(int number) {
        assertThatNoException()
                .isThrownBy(() -> MenuRepository.findByNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 7, 20, 23})
    void 메뉴_찾기_예외_테스트(int number) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> MenuRepository.findByNumber(number));
    }
}
