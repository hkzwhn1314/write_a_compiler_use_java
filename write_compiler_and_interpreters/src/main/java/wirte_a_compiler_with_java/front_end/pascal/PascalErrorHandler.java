package wirte_a_compiler_with_java.front_end.pascal;

import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.front_end.Parse.Parser;
import wirte_a_compiler_with_java.front_end.token.Token;

import static wirte_a_compiler_with_java.front_end.Message.MessageType.SYNTAX_ERROR;
import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.TOO_MANY_ERRORS;

/**
 * @Author zhaocenliu
 * @create 2023/2/1 1:23 PM
 */
public class PascalErrorHandler {
    private static final int MAX_ERRORS = 25;
    private static int errorCount = 0; // count of syntax errors

    public void flag(Token token, PascalErrorCode errorCode, Parser parser) {
        // Notify the parser&apos;s listeners.
        parser.sendMessage(new Message(SYNTAX_ERROR,
                new Object[]{token.getLineNumber(),
                        token.getPosition(),
                        token.getText(),
                        errorCode.toString()}));
        if (++errorCount > MAX_ERRORS) {
            abortTranslation(TOO_MANY_ERRORS, parser);
        }
    }

    public void abortTranslation(PascalErrorCode errorCode, Parser parser) {
        // Notify the parser&apos;s listeners and then abort.
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(new Message(SYNTAX_ERROR,
                new Object[]{0,
                        0,
                        "",
                        fatalText}));
        System.exit(errorCode.getStatus());
    }

    public static int getErrorCount() {
        return errorCount;
    }
}
