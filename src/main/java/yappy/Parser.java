package yappy;

/**
 * Parses user input commands for the Yappy chatbot.
 * 
 * <p>Grammar:
 * <pre>
 * expression       ::= list-expression
 *                    | mark-expression
 *                    | unmark-expression
 *                    | todo-expression
 *                    | deadline-expression
 *                    | event-expression
 *
 * list-expression     ::= LIST
 * mark-expression     ::= MARK int
 * unmark-expression   ::= UNMARK int
 * todo-expression     ::= TODO String
 * deadline-expression ::= DEADLINE String /by String
 * event-expression    ::= EVENT String /from String /to String
 * </pre>
 */

