package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                char dataChar = (char) data;
                if (filter.test(dataChar)) {
                    output.append(dataChar);
                }
            }
            return output.toString();
        }
    }
}
