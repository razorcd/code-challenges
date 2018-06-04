package com.other;

import java.util.Scanner;

public class UserStdIn {

    public static void main(String[] args) {
        System.out.println("Enter something...");
        Scanner in = new Scanner(System.in);

        System.out.println("Input line was: " + in.nextLine());
        System.out.println("Input pattern was: " + in.next("[a-z]"));   // throws InputMismatchException if no regexp match
        System.out.println("Input integer was: " + in.nextInt());   // throws InputMismatchException if no int match
    }

}
