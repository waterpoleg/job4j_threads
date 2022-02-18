package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(
                     Paths.get(new URL(url).getPath()).getFileName().toString())) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int bytesWritten = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWritten += bytesRead;
                if (bytesWritten >= speed) {
                    long deltaTime = System.currentTimeMillis() - startTime;
                    if (deltaTime < 1000) {
                        Thread.sleep(1000 - deltaTime);
                    }
                    startTime = System.currentTimeMillis();
                }
            }
            System.out.printf("\r load: " + bytesWritten + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] getValidatedArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        return args;
    }

    public static void main(String[] args) throws InterruptedException {
        String[] validArgs = getValidatedArgs(args);
        String url = validArgs[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
