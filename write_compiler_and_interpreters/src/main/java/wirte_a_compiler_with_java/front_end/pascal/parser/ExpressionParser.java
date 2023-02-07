package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.inter.TokenType;
import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNodeType;

import java.util.EnumSet;
import java.util.HashMap;

import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.*;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl.*;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 9:02 PM
 */
public class ExpressionParser extends StatementParser {
    public ExpressionParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        return parseExpression(token);
    }

    // Set of relational operators.
    private static final EnumSet<PascalTokenType> REL_OPS =
            EnumSet.of(EQUALS, NOT_EQUALS, LESS_THAN, LESS_EQUALS,
                    GREATER_THAN, GREATER_EQUALS);
    private static final HashMap<PascalTokenType, ICodeNodeType>
            REL_OPS_MAP = new HashMap<PascalTokenType, ICodeNodeType>();

    static {
        REL_OPS_MAP.put(EQUALS, EQ);
        REL_OPS_MAP.put(NOT_EQUALS, NE);
        REL_OPS_MAP.put(LESS_THAN, LT);
        REL_OPS_MAP.put(LESS_EQUALS, LE);
        REL_OPS_MAP.put(GREATER_THAN, GT);
        REL_OPS_MAP.put(GREATER_EQUALS, GE);
    }

    ;

    // TODO
    private ICodeNode parseExpression(Token token) throws Exception {
        // Parse a simple expression and make the root of its tree
        // the root node.
        ICodeNode rootNode = parseSimpleExpression(token);
        token = currentToken();
        TokenType tokenType = token.getType();
        return null;
    }

    private ICodeNode parseSimpleExpression(Token token) {
        return null;
    }

}
