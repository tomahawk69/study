package algorithms.tasks;

public class LatinConversion {

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        for (int i = LatinChar.values().length - 1; i >= 0; i--) {
            LatinChar current = LatinChar.values()[i];
            while (num >= current.v) {
                num = num - current.v;
                result.append(current.c);
            }
        }
        return result.toString();
    }

    enum LatinChar {
        I("I", 1),
        IV("IV", 4),
        V("V", 5),
        IX("IX", 9),
        X("X", 10),
        XL("XL", 40),
        L("L", 50),
        XC("XC", 90),
        C("C", 100),
        CD("CD", 400),
        D("D", 500),
        CM("CM", 900),
        M("M", 1000);

        private final String c;
        private final int v;

        LatinChar(String c, int v) {
            this.c = c;
            this.v = v;
        }

    }

}
