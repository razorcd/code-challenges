package com.theinquiringmanager;

import com.theinquiringmanager.Main;
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

public class LineByLineInputTest {
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
        startApplicationThread();
//        assertThat("Start1\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream("11");
        printToApplicationStream("1 150 0");
        printToApplicationStream("1 3 10");
        printToApplicationStream("2 40");

        assertThat("150\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream("1 143 59");
        printToApplicationStream("2 59");

        assertThat("150\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream("1 100 60");
        printToApplicationStream("2 60");

        assertThat("143\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream("1 159 61");
        printToApplicationStream("2 61");

        assertThat("159\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream("2 120");

        assertThat("159\n", is(consoleOutputStream.toString()));

        consoleOutputStream.reset(); // clear console input
        printToApplicationStream("2 121");

        assertThat("-1\n", is(consoleOutputStream.toString()));
    }

    // print something to waiting console
    private void printToApplicationStream(String line) throws IOException, InterruptedException {
        testDataStream.write((line + "\n").getBytes());  // write new data
        testDataStream.flush();  // send it trough
        Thread.sleep(20L);  // to allow main application to process data
    }


    private void startApplicationThread() throws InterruptedException {
        es.execute(() -> Main.main(null));
        Thread.sleep(100L); // ensure thread is initialised
    }
}