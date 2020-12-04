package algorithms.tasks;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordCrackerTest {

    static class PasswordCrackerRequest {
        List<String> passwords;
        String loginAttempt;

        public PasswordCrackerRequest(List<String> passwords, String loginAttempt) {
            this.passwords = passwords;
            this.loginAttempt = loginAttempt;
        }
    }

    @Test
    void case1() {
        List<String> result = loadIn("/passwordcracker/01.in").stream()
                .map(request -> PasswordCracker.passwordCracker(request.passwords, request.loginAttempt))
                .collect(Collectors.toList());
        List<String> expectedResult = loadResult("/passwordcracker/01.out");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void case2() {
        List<String> result = loadIn("/passwordcracker/02.in").stream()
                .map(request -> PasswordCracker.passwordCracker(request.passwords, request.loginAttempt))
                .collect(Collectors.toList());
        List<String> expectedResult = loadResult("/passwordcracker/02.out");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void case3() {
        List<String> result = loadIn("/passwordcracker/03.in").stream()
                .map(request -> PasswordCracker.passwordCracker(request.passwords, request.loginAttempt))
                .collect(Collectors.toList());
        List<String> expectedResult = loadResult("/passwordcracker/03.out");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void case4() {
        List<String> result = loadIn("/passwordcracker/04.in").stream()
                .map(request -> PasswordCracker.passwordCracker(request.passwords, request.loginAttempt))
                .collect(Collectors.toList());
        List<String> expectedResult = loadResult("/passwordcracker/04.out");
        assertThat(result).isEqualTo(expectedResult);
    }

    private List<PasswordCrackerRequest> loadIn(String resourceName) {
        InputStream inputStream = this.getClass().getResourceAsStream(resourceName);

        try (Scanner scanner = new Scanner(inputStream)) {
            int queriesCount = Integer.parseInt(scanner.nextLine().trim());
            List<PasswordCrackerRequest> result = new ArrayList<>(queriesCount);
            while (queriesCount-- > 0) {
                int passwordsCount = Integer.parseInt(scanner.nextLine().trim());
                List<String> passwords = Arrays.asList(scanner.nextLine().trim().split(" "));
                String loginAttempt = scanner.nextLine();
                result.add(new PasswordCrackerRequest(passwords, loginAttempt));
            }
            return result;
        }
    }

    private List<String> loadResult(String resourceName) {
        InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
        List<String> expectedResult = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()) {
                expectedResult.add(scanner.nextLine());
            }
        }
        return expectedResult;
    }

}