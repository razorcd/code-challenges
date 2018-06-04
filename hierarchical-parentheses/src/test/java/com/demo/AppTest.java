package com.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest
{
    @Test
    public void shouldCheckEmptyString(){
        assertTrue("Should validate empty string equation.", new Equation("").isValid());
    }

    @Test
    public void shouldCheckEquationWithNoParenthesis() {
        assertTrue("Should validate equation with no parenthesis.", new Equation("5 * 5").isValid());
    }

    @Test
    public void shouldCheckEquationWithOneOpenParenthesis() {
        assertFalse("Should invalidate equation with one open parenthesis.", new Equation("2 * (2 + 1").isValid());
    }

    @Test
    public void shouldCheckEquationWithOneOpenAndOneClosedParenthesis() {
        assertTrue("Should validate equation with one open and one close parenthesis.", new Equation("2 * (2 + 1)").isValid());
    }

    @Test
    public void shouldCheckEquationWithTwoOpenAndOneClosedParenthesis() {
        assertFalse("Should invalidate equation with two open and one close parenthesis.", new Equation("(2 * (2 + 1)").isValid());
    }

    @Test
    public void shouldCheckEquationWithMultipleInOrderOpenAndClosedRoundParenthesis() {
        assertTrue("Should validate equation with multiple in order open and close round parenthesis.",
                new Equation("(2 + 5) * (2 + 1)").isValid());
    }

    @Test
    public void shouldCheckEquationWithMultipleInOrderOpenAndClosedRoundAndSquareParenthesis() {
        assertTrue("Should validate equation with multiple in order open and close round and square parenthesis.",
                new Equation("[(2 + 5) * (2 + 1)] * 2").isValid());
    }

    @Test
    public void shouldCheckEquationWithOneOpenSquareAndMultipleInOrderOpenAndClosedRoundParenthesis() {
        assertFalse("Should validate equation with one square brace and multiple in order open and close round parenthesis.",
                new Equation("[(2 + 5) * (2 + 1) * 2").isValid());
    }

    @Test
    public void shouldCheckEquationWithMultipleInOrderOpenAndClosedRoundAndSquareAndCurlyParenthesis() {
        assertTrue("Should validate equation with multiple in order open and close round, square and curly parenthesis.",
                new Equation("{[(2 + 5) * (2 + 1)] * 2 + 1} * 3").isValid());
    }

    @Test
    public void shouldCheckEquationWithOneOpenRoundAndOneClosedSquareParenthesis() {
        assertFalse("Should invalidate equation with one open round and one closed square parenthesis.",
                new Equation("(2 + 5] * 2").isValid());
    }

    @Test
    public void shouldCheckEquationWithOneOpenCurlyAndMultipleInOrderOpenAndClosedRoundAndSquareParenthesis() {
        assertFalse("Should validate equation with one open curly and multiple in order open and close round, square parenthesis.",
                new Equation("{[(2 + 5) * (2 + 1)] * 2 + 1 * 3").isValid());
    }

    @Test
    public void shouldCheckEquationWithMultipleHierarchicalOpenAndClosedRoundAndSquareAndCurlyParenthesisWithMultipleInnerParenthesis() {
        assertTrue("Should validate equation with multiple hierarchical open and close round, square and curly parenthesis where there are multiple different inner parenthesis groups (1).",
                new Equation("{(4 + 1) * { 5 * [( 5 ^ 2 - 4 ^ 2 ) * 2 ]}} / 2").isValid());
        assertTrue("Should validate equation with multiple hierarchical open and close round, square and curly parenthesis where there are multiple different inner parenthesis groups (2).",
                new Equation("{[(2 + 5) * 2] + 1} * (2 + 1) * {[(2 + 5) * 2] + 1} * 2").isValid());
        assertTrue("Should validate equation with multiple hierarchical open and close round, square and curly parenthesis and with multiple root curly braces.",
                new Equation("{[(2 + 5) * 2] + 1} * (2 + 1) * {[(2 + 5) * 2] + 1} / {[(2 + 5) * 2] + 1} * (2 + 1) * {[(2 + 5) * 2] + 1} * 2").isValid());
        assertTrue("Should validate equation with multiple hierarchical open and close round, square and curly parenthesis and with multiple root curly braces.",
                new Equation("{{[(2 + 5) * 2] + 1} * (2 + 1) * {[(2 + 5) * 2] + 1} / {[(2 + 5) * 2] + 1} * (2 + 1) * {[(2 + 5) * 2] + 1} * 2} / 5").isValid());
    }

    @Test
    public void shouldCheckEquationWithMultipleHierarchicalOpenAndClosedSquareAndCurlyParenthesisWithoutInnerRoundParenthesis() {
        assertFalse("Should invalidate equation with multiple hierarchical open and close square and curly parenthesis without inner round parenthesis.",
                new Equation("{[2 + 5] * 2} * 3").isValid());
    }

    @Test
    public void shouldCheckEquationWithWrongHierarchicalOrderOfOpenAndClosedSquareAndCurlyAndRoundParenthesis() {
        assertFalse("Should invalidate equation with wrong hierarchical order of open and closed square, curly and round parenthesis.",
                new Equation("{([2 + 5] / 2) * 2} * 3").isValid());
    }

    @Test
    public void shouldCheckEquationWithMultipleNotHierarchicalOpenAndClosedRoundAndSquareAndCurlyParenthesis() {
        assertFalse("Should invalidate equation with multiple NOT hierarchical open and close round, square and curly parenthesis (1).",
                new Equation("[({2 + 5} * (2 + 1)) * 2 + 1] * 3").isValid());
        assertFalse("Should invalidate equation with multiple NOT hierarchical open and close round, square and curly parenthesis (2).",
                new Equation("{2 * (2 + 1)} * 3").isValid());
        assertFalse("Should invalidate equation with multiple NOT hierarchical open and close round, square and curly parenthesis (3).",
                new Equation("{[2 + 5] * 2} * 3 / (3 - 1)").isValid());
        assertFalse("Should invalidate equation with multiple NOT hierarchical open and close round, square and curly parenthesis (4).",
                new Equation("[{2 + 5} * (2 + 1) * {2 + 1}] * 2").isValid());
        assertFalse("Should invalidate equation with multiple NOT hierarchical open and close round, square and curly parenthesis (5).",
            new Equation("{{[(2 + 5) * 2] + 1} * (2 + 1) * {[(2 + 5) * 2] + 1} / {[(2 + 5) * 2] + 1} * (2 + 1) * {[2 + 5 * 2] + 1} * 2} / 5").isValid());
    }


//    @Test
//    public void shouldCheckEquationWithExtraBraces() {
//        assertTrue("Should validate equation with extra braces.",
//                new Equation("<{[(2 + 5) * (2 + 1)] * 2} + 1> * 3").isValid());
//    }

}
