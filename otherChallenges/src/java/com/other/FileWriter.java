package com.other;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {

    public static void main(String[] args) throws IOException {
        Path currentPath = FileSystems.getDefault().getPath("").toAbsolutePath(); // path to root of project (not current file)

        Path filePath = Paths.get(currentPath.toString(), "files", "newFile2.txt");  // create file path
        File file = new File(filePath.toString());    // creates file but Path would be enough for reading.
        System.out.println("File exists: "+file.exists()+'\n');


        // 1. Create file with NIO
        //  Files.createFile(filePath);   // create empty
        Files.write(filePath, "Write with NIO".getBytes());   // overwrites file and adds the content

        // 2. Write byte by byte
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            for (byte b : "Write byte by byte and \nnew line".getBytes()) {
                outputStream.write(b);    // creates and writes to file
        //        outputStream.flush();  // to write any buffered bytes to file
            }
        }
    }
}
