package pw.ollie.skyprint.edit;

/**
 * A representation of all characters which can be written by SkyPrint. Each of
 * the characters are represented by a two-dimensional array of ones and zeroes,
 * which represent whether there is a block in the location
 */
public enum SkyCharacter {
    A(
            'A',
            new int[][] { new int[] { 0, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
    B(
            'B',
            new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 } }),
    C(
            'C',
            new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 0, 1, 1 } }),
    D(
            'D',
            new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 } }),
    E(
            'E',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
    F(
            'F',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 } }),
    G(
            'G',
            new int[][] { new int[] { 0, 1, 1, 1 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 1 } }),
    H(
            'H',
            new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
    I(
            'I',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 1, 1, 1 } }),
    J(
            'J',
            new int[][] { new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 } }),
    K(
            'K',
            new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
    L(
            'L',
            new int[][] { new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
    M(
            'M',
            new int[][] { new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 1, 0, 1, 1 }, new int[] { 1, 0, 1, 0, 1 }, new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1 } }),
    N(
            'N',
            new int[][] { new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 1, 0, 0, 1 }, new int[] { 1, 0, 1, 0, 1 }, new int[] { 1, 0, 0, 1, 1 }, new int[] { 1, 0, 0, 0, 1 } }),
    O(
            'O',
            new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
    P(
            'P',
            new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 } }),
    Q(
            'Q',
            new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 1 } }),
    R(
            'R',
            new int[][] { new int[] { 1, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 1, 0, 0 }, new int[] { 1, 0, 1, 0 }, new int[] { 1, 0, 0, 1 } }),
    S(
            'S',
            new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 1 }, new int[] { 1, 1, 0 } }),
    T(
            'T',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 } }),
    U(
            'U',
            new int[][] { new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
    V(
            'V',
            new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 } }),
    W(
            'W',
            new int[][] { new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 0, 1, 0, 1 }, new int[] { 1, 1, 0, 1, 1 }, new int[] { 1, 1, 0, 1, 1 } }),
    X(
            'X',
            new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
    Y(
            'Y',
            new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 } }),
    Z(
            'Z',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
    SPACE(
            ' ',
            new int[][] { new int[] { 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 0 } }),
    ZERO(
            '0',
            new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
    ONE(
            '1',
            new int[][] { new int[] { 1, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 1, 1, 1 } }),
    TWO(
            '2',
            new int[][] { new int[] { 0, 1, 1, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 1, 1, 1, 1 } }),
    THREE(
            '3',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 1, 1, 1 } }),
    FOUR(
            '4',
            new int[][] { new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 1, 0 }, new int[] { 1, 1, 1, 1 }, new int[] { 0, 0, 1, 0 } }),
    FIVE(
            '5',
            new int[][] { new int[] { 1, 1, 1, 1 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 1, 1, 0 }, new int[] { 0, 0, 0, 1 }, new int[] { 1, 1, 1, 1 } }),
    SIX(
            '6',
            new int[][] { new int[] { 0, 1, 1, 1 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
    SEVEN(
            '7',
            new int[][] { new int[] { 1, 1, 1, 1 }, new int[] { 0, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 1, 0, 0, 0 } }),
    EIGHT(
            '8',
            new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
    NINE(
            '9',
            new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 1 }, new int[] { 0, 0, 0, 1 }, new int[] { 0, 0, 0, 1 } }),
    FULLSTOP(
            '.',
            new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
    COMMA(
            ',',
            new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
    EXCLAMATION(
            '!',
            new int[][] { new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
    QUESTION(
            '?',
            new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
    PLUS(
            '+',
            new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 } }),
    MINUS(
            '-',
            new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 } }),
    APOSTROPHE('\'', new int[][] { new int[] { 1 } }),
    POUND(
            '\u00A3',
            new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
    SPEECH('"', new int[][] { new int[] { 1, 0, 1 } }),
    BRACKET_OPEN(
            '(',
            new int[][] { new int[] { 0, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 0, 1, 0 } }),
    BRACKET_CLOSE(
            ')',
            new int[][] { new int[] { 0, 1, 0 }, new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 0 } }),
    UNDERSCORE(
            '_',
            new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 } }),
    EQUALS(
            '=',
            new int[][] { new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 0, 0 } }),
    SLASH(
            '/',
            new int[][] { new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 1, 0, 0, 0 } }),
    BACKSLASH(
            '\\',
            new int[][] { new int[] { 0, 0, 0, 0 }, new int[] { 1, 0, 0, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 0, 0, 1 } });

    /**
     * The actual character being represented
     */
    private final char character;
    /**
     * The raw values of the shape required for the character to be formed in
     * minecraft
     */
    private final int[][] binary;

    /**
     * Constructs a new SkyCharacter from the given arguments
     * 
     * @param character The raw char this SkyCharacter represents
     * @param binary A representation of the shapes required to build the
     *        character in Minecraft
     */
    private SkyCharacter(final char character, final int[][] binary) {
        this.character = character;
        this.binary = binary;
    }

    /**
     * Gets the char for this SkyCharacter
     * 
     * @return The char for this SkyCharacter
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Gets the representation of this character as a two-dimensional array
     * 
     * @return The representation in 1s and 0s of the shape of this character
     */
    public int[][] getRaw() {
        return binary;
    }

    /**
     * Gets the SkyCharacter object from the given char, returning null if the
     * given char has no SkyCharacter object
     * 
     * @param character The char to get the SkyCharacter from
     * @return The SkyCharacter for the given char
     */
    public static SkyCharacter fromChar(final char character) {
        for (final SkyCharacter sc : values()) {
            if (sc.getCharacter() == character) {
                return sc;
            }
        }
        return null;
    }
}
