package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.inter.TokenType;
import wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode;
import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType;
import wirte_a_compiler_with_java.front_end.token.EofToken;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;

import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.MISSING_SEMICOLON;
import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.UNEXPECTED_TOKEN;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.IDENTIFIER;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.SEMICOLON;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeKeyImpl.LINE;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl.NO_OP;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 2:23 PM
 */
public class StatementParser extends PascalParserTD {

    public StatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        ICodeNode statementNode = null;
        switch ((PascalTokenType) token.getType()) {
            case BEGIN: {
                CompoundStatementParser compoundParser =
                        new CompoundStatementParser(this);
                statementNode = compoundParser.parse(token);
                break;
            }

            // An assignment statement begins with a variable's identifier.
            case IDENTIFIER: {
                AssignmentStatementParser assignmentParser =
                        new AssignmentStatementParser(this);
                statementNode = assignmentParser.parse(token);
                break;
            }

            default: {
                statementNode = ICodeFactory.createICodeNode(NO_OP);
                break;
            }
        }

        setLineNumber(statementNode, token);
        return statementNode;

    }

    protected void setLineNumber(ICodeNode node, Token token) {
        if (node != null) {
            node.setAttribute(LINE, token.getLineNumber());
        }
    }

    //TODO
    //END, MISSING_END
    protected void parseList(Token token, ICodeNode parentNode,
                             PascalTokenType terminator,
                             PascalErrorCode errorCode) throws Exception {
        while (!(token instanceof EofToken) &&
                (token.getType() != terminator)) {
            ICodeNode statementNode = parse(token);
            parentNode.addChild(statementNode);
            token = currentToken();
            TokenType tokenType = token.getType();
            if (tokenType == SEMICOLON) {
                token = nextToken();  // consume the ;
            } else if (tokenType == IDENTIFIER) {
                errorHandler.flag(token, MISSING_SEMICOLON, this);
            } else if (tokenType != terminator) {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
                token = nextToken();  // consume the unexpected token
            }


        }

        if (token.getType() == terminator) {
            token = nextToken();  // consume the terminator token
        } else {
            errorHandler.flag(token, errorCode, this);
        }
    }
}
