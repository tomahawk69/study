package algorithms.tasks;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class KunduAndTreeTest {

    @Test
    void case1() {
        InputStream inputStream = this.getClass().getResourceAsStream("/kundluandtree/01.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        KunduAndTree.main(new String[0]);
        compare("/kundluandtree/01.out", outputStream.toString());
    }

    @Test
    void case2() {
        InputStream inputStream = this.getClass().getResourceAsStream("/kundluandtree/02.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        KunduAndTree.main(new String[0]);
        compare("/kundluandtree/02.out", outputStream.toString());
    }

    private void compare(String fileName, String result) {
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);
        try (Scanner scanner = new Scanner(inputStream)) {
            String expected = scanner.next();
            assertThat(result.trim()).isEqualTo(expected.trim());
        }
    }
}