package com.doesitfit;

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

public class MainTest {
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
        String inputData =
                "4 5\n" +
                "3\n" +
                "R 1 2\n" +
                "R 5 5\n" +
                "C 2";

        String expectedResponse =
                "YES\n" +
                "NO\n" +
                "YES";

        startApplicationThread();

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream(inputData);

        assertThat(expectedResponse, is(consoleOutputStream.toString().trim()));
    }

    @Test
    public void testMainObliqueRectangleFit1() throws InterruptedException, IOException {
        String inputData =
                "5 5\n" +
                "2\n" +
                "R 6 1\n" +
                "R 6 3";

        String expectedResponse =
                "YES\n" +
                "NO";

        startApplicationThread();

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream(inputData);

        assertThat(expectedResponse, is(consoleOutputStream.toString().trim()));
    }

    @Test
    public void testMainObliqueRectangleFit2() throws InterruptedException, IOException {
        String inputData =
                "1000 1000\n" +
                "10\n" +
                "R 1000 1000\n" +
                "R 994 1000\n" +
                "R 100 1000\n" +
                "R 1000 1000\n" +
                "R 995 1000\n" +
                "R 1000 1000\n" +
                "R 1000 997\n" +
                "R 992 997\n" +
                "R 994 1000\n" +
                "R 990 996";

        String expectedResponse =
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES";

        startApplicationThread();

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream(inputData);

        assertThat(expectedResponse, is(consoleOutputStream.toString().trim()));
    }

    @Test
    public void testMainObliqueRectangleFit3() throws InterruptedException, IOException {
        String inputData =
                "900 900\n" +
                "37\n" +
                "R 999 999\n" +
                "R 995 994\n" +
                "R 1000 1000\n" +
                "R 94 990\n" +
                "R 103 992\n" +
                "R 105 996\n" +
                "R 106 1000\n" +
                "R 103 1000\n" +
                "R 103 1000\n" +
                "R 91 993\n" +
                "R 110 1000\n" +
                "R 990 99\n" +
                "R 997 95\n" +
                "R 992 93\n" +
                "R 1000 92\n" +
                "R 999 92\n" +
                "R 994 96\n" +
                "R 1000 103\n" +
                "R 1000 109\n" +
                "R 990 92\n" +
                "R 997 93\n" +
                "R 1000 103\n" +
                "R 994 90\n" +
                "R 1000 101\n" +
                "R 1000 107\n" +
                "R 998 109\n" +
                "R 995 101\n" +
                "R 106 996\n" +
                "R 99 1000\n" +
                "R 103 1000\n" +
                "R 108 996\n" +
                "R 103 990\n" +
                "R 103 992\n" +
                "R 90 1000\n" +
                "R 101 993\n" +
                "R 97 1000\n" +
                "R 99 1000";

        String expectedResponse =
                "NO\n" +
                "NO\n" +
                "NO\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES";

        startApplicationThread();

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream(inputData);

        assertThat(expectedResponse, is(consoleOutputStream.toString().trim()));
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


