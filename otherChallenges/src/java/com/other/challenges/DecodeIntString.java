package com.other.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Facebook interview questions:
 *
 * Given the following rules for characters:
 * {
 *   a -> 1
 *   b -> 2
 *   ...
 *   z -> 26
 * }
 *
 * And an input string formed of numbers between 0 and 9. (example: "12", "125406" or "0123")
 * Then return all possible decryptions of this input string by matching numbers between 1 and 26 to the rules hash.
 * For example:
 *   - input "12" can be decoded to ["ab", "l"].
 *   - input "12345" can be decoded to ['abcde', 'awde', 'lcde'].
 *
 */
public class DecodeIntString {

    /**
     * Defines the number of digits a character int value can have.
     */
    private static final int[] INT_LENGTHS = {1,2};

    private final String intInput;

    /**
     * Constructor.
     *
     * @param intInput the int encoded string.
     */
    DecodeIntString(final String intInput) {
        this.intInput = intInput;
    }


    /**
     * Decode the int encoded string.
     * @return {@code List<String>} list of all possible decodings.
     */
    public List<String> decode() {
        if (intInput.isEmpty() || intInput.charAt(0) == '0') return Collections.emptyList();
        return recursiveDecode(0, "", new ArrayList<>());
    }

    private List<String> recursiveDecode(final int currentPos, final String currentResult, final List<String> results) {
        for (int intLength : INT_LENGTHS) {
            Optional<Character> character = getCharacterByStringPosition(currentPos, intLength);
            Optional<String> tempCurrentResult = character.map(c -> currentResult.concat(c.toString()));

            if (!tempCurrentResult.isPresent()) return results; // if not valid decoded character found then STEP BACK

            if (currentPos == intInput.length()-intLength) {
                results.add(tempCurrentResult.get());
                return results;
            } else {
                recursiveDecode(currentPos + intLength, tempCurrentResult.orElse(currentResult), results);
            }
        }
        return results;
    }

    /**
     * Returns the decoded character of the input string by position of cursor in input string and length of integer value.
     *
     * @param currentPos position of cursor in input string.
     * @param digitsLength length of integer value.
     * @return {@code [Optional<Character>]} the decoded character.
     */
    private Optional<Character> getCharacterByStringPosition(final int currentPos, final int digitsLength) {
        Optional<Integer> intValue = parseAndValidateInt(intInput.substring(currentPos, currentPos+digitsLength));
        return intValue.map(this::getCharacterFor);
    }

    /**
     * Parses and validates input string into integer.
     *
     * @param intString integer as a string
     * @return {@code [Optional<Integer>]} Optional of the parsed string or empty if invalid.
     */
    private Optional<Integer> parseAndValidateInt(final String intString) {
        if (intString.isEmpty() || intString.charAt(0) == '0') return Optional.empty();

        int intValue = Integer.parseInt(intString);
        if (intValue<1 || intValue >= 26) return Optional.empty();

        return Optional.of(intValue);
    }

    /**
     * Decoding rules. Convert an int value to it's corresponding character.
     *
     * @param val the int to covert by.
     * @return {@code [Character]} the corresponding character
     */
    private Character getCharacterFor(final int val) {
        return (char) (val+64+32);
    }
}
