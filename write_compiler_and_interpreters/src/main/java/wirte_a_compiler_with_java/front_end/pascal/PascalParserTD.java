package wirte_a_compiler_with_java.front_end.pascal;

import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.front_end.Parse.Parser;
import wirte_a_compiler_with_java.front_end.Parse.Scanner;
import wirte_a_compiler_with_java.front_end.inter.TokenType;
import wirte_a_compiler_with_java.front_end.token.EofToken;
import wirte_a_compiler_with_java.front_end.token.Token;


import static wirte_a_compiler_with_java.front_end.Message.MessageType.PARSER_SUMMARY;
import static wirte_a_compiler_with_java.front_end.Message.MessageType.TOKEN;
import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.IO_ERROR;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.ERROR;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 12:48 AM
 */
public class PascalParserTD extends Parser {

    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    @Override
    public int getErrorCount() {
        return 0;
    }

    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();
        try {
            // Loop over each token until the end of file.
            while (!((token = nextToken()) instanceof EofToken)) {
                TokenType tokenType = token.getType();
                if (tokenType != ERROR) {
                    // Format each token.
                    sendMessage(new Message(TOKEN,
                            new Object[]{token.getLineNumber(),
                                    token.getPosition(),
                                    tokenType,
                                    token.getText(),
                                    token.getValue()}));
                } else {
                    errorHandler.flag(token, (PascalErrorCode) token.getValue(),
                            this);
                }
            }
            // Send the parser summary message.
            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(PARSER_SUMMARY,
                    new Number[]{token.getLineNumber(),
                            getErrorCount(),
                            elapsedTime}));
        } catch (java.io.IOException ex) {
            errorHandler.abortTranslation(IO_ERROR, this);
        }
    }
}
