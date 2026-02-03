package yappy;

/**
 * Enum representing the different types of command tokens.
 * Each token type corresponds to a user command.
 */
public enum TokenType {
    EXIT("exit"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String commandWord;

    TokenType(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Gets the command word for this token type.
     *
     * @return The command word string
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Parses a string to find the matching TokenType.
     *
     * @param text The command word to parse
     * @return The matching TokenType, or null if not found
     */
    public static TokenType fromString(String text) {
        for (TokenType type : TokenType.values()) {
            if (type.commandWord.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}