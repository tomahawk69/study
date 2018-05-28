package org.study.regexp;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceFunction {

    public static String replace(String searchString, String patternString, Function<MatchResult, String> mapping) {
        Matcher matcher = Pattern.compile(patternString).matcher(searchString);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            MatchResult matchResult = matcher.toMatchResult();
            matcher.appendReplacement(sb, mapping.apply(matchResult).replaceAll("\\$", "\\\\\\$"));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
