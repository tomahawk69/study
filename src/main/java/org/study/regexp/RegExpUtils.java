package org.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yur on 02.11.2015.
 */
public class RegExpUtils {
    /*
    This method counts and returns the total number of syllables in the
    document (the stored text). To count the number of syllables in a word,
    you should use the rule that every contiguous sequence of one or more vowels,
    except for a lone “e” at the end of a word if the word has another vowel
    or set of contiguous vowels, makes up one syllable. You should consider
    y a vowel. Under these rules the words “the”, “fly”, “yes”, “cave” and
    “double” all have 1 syllable, but "segue" has two syllables. Notice that
    this isn’t exactly correct (“double” actually has 2 syllables),
    but it’s close enough for our purposes. Here are some more examples
    with the number of syllables your method should return to help you:
        "contiguous" (3 syllables), "sleepy" (2 syllables),
        "obvious" (2 syllables), "toga" (2 syllables).
    Notice that our rules get a lot wrong, especially when you have more
    than 2 vowels in a row, but these are the rules we will test you against.
    */
    private static Pattern pattern = Pattern.compile("[aeiouy]");

    public static int getCount(String source) {
        int result = 0;
        boolean isLastVowel = false;

        for (int i = 0; i < source.length(); i++) {
            String letter = source.substring(i, i + 1);
            boolean isVowel = pattern.matcher(letter).find();
            if (isVowel) {
                if (!isLastVowel) {
                    if (i < source.length() - 1 || result == 0 || !letter.equals("e"))
                        result++;
                    isLastVowel = true;
                }
            } else {
                isLastVowel = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] tests = {"teste", "contiguous", "sleepy", "obvious", "toga", "segue", "double", "the"};

        for (String test : tests)
            System.out.println(test + " : " + getCount(test));
    }
}
