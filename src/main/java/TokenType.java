public enum TokenType {
    LIST("^list"),
    MARK("^mark"),
    UNMARK("^unmark"),
    DEADLINE("^deadline");

    private final String pattern;

    TokenType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
    