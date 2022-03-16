package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private synchronized String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                char dataChar = (char) data;
                if (filter.test(dataChar)) {
                    output.append(dataChar);
                }
            }
            return output.toString();
        }
    }

    public synchronized String getContent() throws IOException {
        return getContent(c -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(c -> c < 0x80);
    }
}
