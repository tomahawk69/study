package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;


class MergingCommunitiesTest {

    private MergingCommunities mergingCommunities;

    @BeforeEach
    void setUp() {
        mergingCommunities = new MergingCommunities();
    }

    @Test
    void shouldPassSampleCase() {
        InputStream inputStream = this.getClass().getResourceAsStream("/mergingcommunities/01.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        mergingCommunities.parse();
        compare("/mergingcommunities/01.out", outputStream.toString());
    }

    @Test
    void shouldPassLongCase() {
        InputStream inputStream = this.getClass().getResourceAsStream("/mergingcommunities/02.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        mergingCommunities.parse();
        compare("/mergingcommunities/02.out", outputStream.toString());
    }

    @Test
    void shouldPass2ndLongCase() {
        InputStream inputStream = this.getClass().getResourceAsStream("/mergingcommunities/03.in");
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        mergingCommunities.parse();
        compare("/mergingcommunities/03.out", outputStream.toString());
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