package com.other;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReader {

    public static void main(String[] args) throws IOException {
    Path currentPath = FileSystems.getDefault().getPath("").toAbsolutePath(); // path to root of project (not current file)

        Path filePath = Paths.get(currentPath.toString(), "files", "file1.txt");  // create file path
        File file = new File(filePath.toString());    // creates file but Path would be enough for reading.
        System.out.println("File exists: "+file.exists()+'\n');

        // Read entire file
        byte[] allContent = Files.readAllBytes(file.toPath());
        System.out.println("*** Read all file content:");
        System.out.println(new String(allContent) + "\n");

        // Read byte by byte
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            System.out.println("*** Read byte stream:");
            do {
              int value = inputStream.read();
              if (value<0) { break;}
              System.out.print((char) value);
            } while(true);
            System.out.println("\n");
        }

        // Read line by line
        List<String> fileLines = Files.readAllLines(file.toPath());
        System.out.println("*** Read all file lines:");
        fileLines.stream().forEach(System.out::println);
        System.out.println();

        // Read stream
        try (Stream<String> stream = Files.lines(file.toPath())) {
            System.out.println("*** Read stream:");
            stream.forEach(System.out::println);
            System.out.println();
        }

        // Iterate with Scanner line by line
        try (Scanner scanner = new Scanner(file.toPath())) {
            System.out.println("*** Itterate with Scanner:");
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }

    }
}
