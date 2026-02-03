package yappy;

/**
 * Enum representing the different types of command tokens.
 * Each token type has an associated regex pattern for matching.
 */
public enum TokenType {
    LIST("^list"),
    FIND("^find"),
    MARK("^mark"),
    UNMARK("^unmark"),
    DELETE("^delete"),
    TODO("^todo"),
    DEADLINE("^deadline"),
    EVENT("^event"),
    EXIT("^exit");

    private final String PATTERN;

    TokenType(String pattern) {
        this.PATTERN = pattern;
    }

    public String getPattern() {
        return PATTERN;
    }
}
    