package org.study.core.formatters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {

    public static void main(String[] args) {
        Float num = -123456.1233f;

        System.out.println(num);
        formatWithNumberFormat(num);
        formatWithStringFormat(num);
        formatWithDecimalSeparator(num);
    }

    private static void formatWithStringFormat(Float num) {
        System.out.println("String.format " + String.format("%(,f", num));
    }

    private static void formatWithNumberFormat(Float num) {
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println("Number.format " + nf.format(num));
    }

    private static void formatWithDecimalSeparator(Float num) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("en", "UK"));
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator('.');
        symbols.setMinusSign('(');
        String pattern = "#,##0.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        System.out.println("DecimalFormatSymbols " + decimalFormat.format(num) + (num < 0 ? ")" : ""));
    }
}
