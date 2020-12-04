package algorithms.tasks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordCracker {

    private final List<String> passwords;
    private final Set<String> passed;
    private final String loginAttempt;

    public PasswordCracker(List<String> passwords, String loginAttempt) {
        this.passwords = passwords;
        this.passwords.sort((p1, p2) -> Integer.compare(p2.length(), p1.length()));
        this.loginAttempt = loginAttempt;
        this.passed = new HashSet<>();
    }

    public String findNext(int index) {
        if (!passed.add(loginAttempt.substring(index))) {
            return "";
        }
        for (String password : passwords) {
            if (loginAttempt.startsWith(password, index)) {
                if (index + password.length() == loginAttempt.length()) {
                    return password;
                }
                String result = findNext(index + password.length());
                if (result.length() != 0) {
                    return password + " " + result;
                }
            }

        }
        return "";
    }

    public static String passwordCracker(List<String> passwords, String loginAttempt) {
        String result = new PasswordCracker(passwords, loginAttempt).findNext(0);
        if (result.length() == 0) {
            return "WRONG PASSWORD";
        } else {
            return result;
        }
    }
}
