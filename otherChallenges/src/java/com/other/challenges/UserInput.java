package com.other.challenges;

public class UserInput {

    public static class TextInput {
        protected String value = "";

        public void add(char c) {
            value += c;
        }

        public String getValue() {
            return value;
        }
    }

    public static class NumericInput extends TextInput {

        @Override
        public void add(char c) {
            try {
                int i = Integer.parseInt(String.valueOf(c));
                value += i;
            } catch (NumberFormatException e) {
                //ignored
            }
        }

    }

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}