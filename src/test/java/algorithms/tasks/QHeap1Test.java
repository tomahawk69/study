package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class QHeap1Test {

    private QHeap1 qHeap1;

    @BeforeEach
    void setUp() {
        qHeap1 = new QHeap1();
    }

    @Test
    void case1() {
        InputStream inputStream = this.getClass().getResourceAsStream("/qheap1/01.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        qHeap1.parse();
        compare("/qheap1/01.out", outputStream.toString());
    }

    @Test
    void case2() {
        InputStream inputStream = this.getClass().getResourceAsStream("/qheap1/02.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        qHeap1.parse();
        compare("/qheap1/02.out", outputStream.toString());
    }

    @Test
    void case3() {
        InputStream inputStream = this.getClass().getResourceAsStream("/qheap1/03.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        qHeap1.parse();
        compare("/qheap1/03.out", outputStream.toString());
    }

    @Test
    void case4() {
        InputStream inputStream = this.getClass().getResourceAsStream("/qheap1/04.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        qHeap1.parse();
        compare("/qheap1/04.out", outputStream.toString());
    }

    private void compare(String expectedFile, String result) {
        InputStream inputStream = this.getClass().getResourceAsStream(expectedFile);
        Scanner scanner = new Scanner(inputStream);
        String expected = "";
        while (scanner.hasNext()) {
            if (expected.length() > 0) expected = expected + "\r\n";
            expected = expected + scanner.nextLine();
        }
        assertThat(Arrays.asList(result.trim().split("\r\n"))).isEqualTo(
                Arrays.asList(expected.trim().split("\r\n")));
    }
}