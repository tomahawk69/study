package algorithms.tasks;

public class SuperDigits {

    static int superDigit(String n, int k) {
        if (n.length() == 1) {
            return Integer.parseInt(n);
        }
        long result = 0;
        for (char c : n.toCharArray()) {
            result = result + charToInt(c);
        }
        return superDigit(String.valueOf(result * k), 1);
    }

    static int charToInt(char c) {
        return c - 48;
    }
}
