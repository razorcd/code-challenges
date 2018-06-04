package com.other.challenges;

public class Palindrome {

    public static boolean isPalindrome(String word) {
        String input = word.toLowerCase();
        for (int i = 0; i < input.length()/2; i++) {
            if (input.charAt(i) != input.charAt(input.length()-i-1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Palindrome.isPalindrome("tot"));
        System.out.println(Palindrome.isPalindrome("toot"));
        System.out.println(Palindrome.isPalindrome("Deleveled"));
        System.out.println(Palindrome.isPalindrome("xDeleveled"));
    }
}
