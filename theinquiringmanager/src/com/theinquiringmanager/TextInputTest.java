package com.theinquiringmanager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TextInputTest {
    final static PrintStream stdout = System.out;
    final static InputStream stdin = System.in;

    ExecutorService es;
    ByteArrayOutputStream consoleOutputStream;
    PipedOutputStream testDataStream;

    PipedInputStream newStdIn;
    PrintStream newStdOut;

    //outputStream is READ THE CONSOLE
    @Before
    public void grabSystemOut() throws UnsupportedEncodingException {
        consoleOutputStream = new ByteArrayOutputStream();
        newStdOut = new PrintStream(consoleOutputStream, true, "UTF-8");
        System.setOut(newStdOut);
    }

    // WRITE TO CONSOLE
    @Before
    public void grabSystemIn() throws IOException, InterruptedException {
        testDataStream = new PipedOutputStream();   //we need an consoleOutputStream stream so we can send data from this thread
        newStdIn = new PipedInputStream();   //we send this data to our new input stream (WRITE TO CONSOLE)
        newStdIn.connect(testDataStream);
        System.setIn(newStdIn);
    }

    @Before
    public void startThreadExecutor() {
        es = Executors.newCachedThreadPool();
    }

    @After
    public void shutdownThreadExecutor() throws InterruptedException {
        es.shutdown();
        boolean finshed = es.awaitTermination(1, TimeUnit.MINUTES);
    }

    @After
    public void closeStreams() throws IOException {
        testDataStream.close();
        newStdIn.close();
        newStdOut.close();
    }

    @AfterClass
    public static void setStdInOutBack() {
        System.setIn(stdin);
        System.setOut(stdout);
    }


    @Test
    public void testMain() throws InterruptedException, IOException {
        String inputData = "11\n" +
                "1 150 0\n" +
                "1 3 10\n" +
                "2 40\n" +
                "1 143 59\n" +
                "2 59\n" +
                "1 100 60\n" +
                "2 60\n" +
                "1 159 61\n" +
                "2 61\n" +
                "2 120\n" +
                "2 121";

        String expectedResponse = "150\n" +
                "150\n" +
                "143\n" +
                "159\n" +
                "159\n" +
                "-1\n";

        startApplicationThread();
//        assertThat("Start1\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream(inputData);

        assertThat(expectedResponse, is(consoleOutputStream.toString()));
    }

    // print something to waiting console
    private void printToApplicationStream(String line) throws IOException, InterruptedException {
        testDataStream.write((line + "\n").getBytes());  // write new data
        testDataStream.flush();  // send it trough
        Thread.sleep(200L);  // to allow main application to process data
    }


    private void startApplicationThread() throws InterruptedException {
        es.execute(() -> Main.main(null));
        Thread.sleep(100L); // ensure thread is initialised
    }
}