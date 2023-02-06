package wirte_a_compiler_with_java.front_end.pascal;

import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.front_end.Parse.Parser;
import wirte_a_compiler_with_java.front_end.Parse.Scanner;
import wirte_a_compiler_with_java.front_end.inter.TokenType;
import wirte_a_compiler_with_java.front_end.pascal.parser.StatementParser;
import wirte_a_compiler_with_java.front_end.token.EofToken;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry;


import static wirte_a_compiler_with_java.front_end.Message.MessageType.PARSER_SUMMARY;
import static wirte_a_compiler_with_java.front_end.Message.MessageType.TOKEN;
import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.*;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.*;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 12:48 AM
 */
public class PascalParserTD extends Parser {

    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    /**
     * Constructor for subclasses.
     *
     * @param parent the parent parser.
     */
    public PascalParserTD(PascalParserTD parent) {
        super(parent.getScanner());
    }

    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    @Override
    public int getErrorCount() {
        return errorHandler.getErrorCount();
    }

    public void parse() throws Exception {

        long startTime = System.currentTimeMillis();
        iCode = ICodeFactory.createICode();

        try {
            Token token = nextToken();
            ICodeNode rootNode = null;

            // Look for the BEGIN token to parse a compound statement.
            if (token.getType() == BEGIN) {
                StatementParser statementParser = new StatementParser(this);
                rootNode = statementParser.parse(token);
                token = currentToken();
            } else {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
            }

            // Look for the final period.
            if (token.getType() != DOT) {
                errorHandler.flag(token, MISSING_PERIOD, this);
            }
            token = currentToken();

            // Set the parse tree root node.
            if (rootNode != null) {
                iCode.setRoot(rootNode);
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
