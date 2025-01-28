package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regexComment = "^#+";
        String regexNonKey = "^=+";
        String regexNonValue = "^([^=]+)=$";
        String regex = "^([^=]+)=(.+)$";
        Pattern patternNonKey = Pattern.compile(regexNonKey);
        Pattern patternComment = Pattern.compile(regexComment);
        Pattern patternNonValue = Pattern.compile(regexNonValue);
        Pattern pattern = Pattern.compile(regex);
        for (String line : lines) {
            Matcher commentMatcher = patternComment.matcher(line);
            Matcher nonKeyMatcher = patternNonKey.matcher(line);
            Matcher nonValueMatcher = patternNonValue.matcher(line);
            Matcher matcher = pattern.matcher(line);
            if (line.equals("=")
                    || (!line.contains("=") && !commentMatcher.find() && !line.isEmpty())
                    || nonKeyMatcher.find()
                    || nonValueMatcher.find()) {
                throw new IllegalArgumentException();
            }
            if (matcher.find()) {
                values.put(matcher.group(1), matcher.group(2));
            }
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);

    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}