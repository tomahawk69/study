package algorithms.tasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeCoinsTest {

    private ChangeCoins changeCoins = new ChangeCoins();

    @ParameterizedTest
    @MethodSource("coinsParams")
    void shouldChangeCoins(int amount, List<Integer> coins, int expected) {
        long result = changeCoins.change(amount, coins);
        assertThat(result).isEqualTo(expected);

    }

    private static Stream<Arguments> coinsParams() {
        return Stream.of(
                Arguments.of(3, List.of(8, 3, 2, 1), 3),
                Arguments.of(1, List.of(8, 3, 2, 1), 1),
                Arguments.of(1, List.of(8, 3, 2), 0),
                Arguments.of(4, List.of(1, 2, 3), 4)
        );
    }

}