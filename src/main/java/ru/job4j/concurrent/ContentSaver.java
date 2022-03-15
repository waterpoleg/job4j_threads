package ru.job4j.concurrent;

import java.io.*;

public class ContentSaver {

    private final File file;

    public ContentSaver(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        }
    }
}
