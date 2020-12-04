package algorithms.tasks;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class ContactsTest {

    @Test
    void case1() {
        String[][] contacts = loadIn("/contacts/01.in");
        int[] expectedResult = loadResult("/contacts/01.out");
        int[] result = Contacts.contacts(contacts);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void case2() {
        String[][] contacts = loadIn("/contacts/02.in");
        int[] expectedResult = loadResult("/contacts/02.out");
        int[] result = Contacts.contacts(contacts);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void case3() {
        String[][] contacts = loadIn("/contacts/03.in");
        int[] expectedResult = loadResult("/contacts/03.out");
        int[] result = Contacts.contacts(contacts);
        assertThat(result).isEqualTo(expectedResult);
    }

    private String[][] loadIn(String resourceName) {
        InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
        try (Scanner scanner = new Scanner(inputStream)) {
            int queriesRows = Integer.parseInt(scanner.nextLine().trim());
            String[][] queries = new String[queriesRows][2];
            for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
                String[] queriesRowItems = scanner.nextLine().split(" ");

                for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                    String queriesItem = queriesRowItems[queriesColumnItr];
                    queries[queriesRowItr][queriesColumnItr] = queriesItem;
                }
            }
            return queries;
        }
    }

    private int[] loadResult(String resourceName) {
        InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
        List<Integer> expectedResult = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()) {
                expectedResult.add(Integer.parseInt(scanner.nextLine()));
            }
        }
        return expectedResult.stream().mapToInt(Integer::intValue).toArray();
    }

}