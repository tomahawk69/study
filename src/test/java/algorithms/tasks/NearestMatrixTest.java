package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NearestMatrixTest {

    private NearestMatrix nearestMatrix;

    @BeforeEach
    void setUp() {
        nearestMatrix = new NearestMatrix();
    }

    @ParameterizedTest
    @CsvSource({
            "54, 7, 8",
            "12, 3, 4",
            "10, 3, 4",
            "8, 3, 3"
    })
    public void shouldCalculateCorrectDimensions(int length, int x, int y) {
        int[] result = nearestMatrix.matrix(length);
        assertThat(result).isNotNull().hasSize(2).containsExactly(x, y);
    }

}