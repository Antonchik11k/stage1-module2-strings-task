package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder regexBuilder = new StringBuilder();
        for (String delimiter : delimiters) {
            if (regexBuilder.length() > 0) {
                regexBuilder.append('|');
            }
            regexBuilder.append(Pattern.quote(delimiter));
        }

        String delimiterRegex = regexBuilder.toString();
        String[] splitArray = source.split(delimiterRegex);

        List<String> result = new ArrayList<>();
        for (String s : splitArray) {
            if (!s.isEmpty()) {
                result.add(s);
            }
        }

        return result;
    }
}
