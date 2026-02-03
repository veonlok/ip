package yappy;

/**
 * Enum representing the different types of command tokens.
 * Each token type has an associated regex pattern for matching.
 */
public enum TokenType {
    LIST("^list"),
    MARK("^mark"),
    UNMARK("^unmark"),
    DEADLINE("^deadline");

    private final String PATTERN;

    /**
     * Creates a TokenType with the specified regex pattern.
     *
     * @param pattern The regex pattern for matching this token type.
     */
    TokenType(String pattern) {
        this.PATTERN = pattern;
    }

    /**
     * Gets the regex pattern associated with this token type.
     *
     * @return The regex pattern string.
     */
    public String getPattern() {
        return PATTERN;
    }
}
    