package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.inter.TokenType;
import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNodeType;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl;

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


    // TODO
    private ICodeNode parseExpression(Token token) throws Exception {
        // Parse a simple expression and make the root of its tree
        // the root node.
        ICodeNode rootNode = parseSimpleExpression(token);
        token = currentToken();
        TokenType tokenType = token.getType();

        if (REL_OPS.contains(tokenType)) {
            ICodeNodeType nodeType = REL_OPS_MAP.get(tokenType);
            ICodeNode opNode = ICodeFactory.createICodeNode(nodeType);
            opNode.addChild(rootNode);
            token = nextToken(); // consume the operator
            // Parse the second simple expression. The operator node adopts
            // the simple expression&apos;s tree as its second child.
            opNode.addChild(parseSimpleExpression(token));
            // The operator node becomes the new root node.
            rootNode = opNode;
        }

        return rootNode;
    }

    // Set of additive operators.
    private static final EnumSet<PascalTokenType> ADD_OPS =
            EnumSet.of(PLUS, MINUS, PascalTokenType.OR);
    // Map additive operator tokens to node types.
    private static final HashMap<PascalTokenType, ICodeNodeTypeImpl>
            ADD_OPS_OPS_MAP = new HashMap<PascalTokenType, ICodeNodeTypeImpl>();

    static {
        ADD_OPS_OPS_MAP.put(PLUS, ADD);
        ADD_OPS_OPS_MAP.put(MINUS, SUBTRACT);
        ADD_OPS_OPS_MAP.put(PascalTokenType.OR, ICodeNodeTypeImpl.OR);
    }


    private ICodeNode parseSimpleExpression(Token token) throws Exception {
        TokenType signType = null;
        TokenType tokenType = token.getType();
        if ((tokenType == PLUS) || (tokenType == MINUS)) {
            signType = tokenType;
            token = nextToken(); // consume the + or -
        }
        // parseTerm
        ICodeNode rootNode = parseTerm(token);
        if (signType == MINUS) {
            ICodeNode negateNode = ICodeFactory.createICodeNode(NEGATE);
            negateNode.addChild(rootNode);
            rootNode = negateNode;
        }

        token = currentToken();
        tokenType = token.getType();
        // Loop over additive operators.
        while (ADD_OPS.contains(tokenType)) {
            // Create a new operator node and adopt the current tree
            // as its first child.
            ICodeNodeType nodeType = ADD_OPS_OPS_MAP.get(tokenType);
            ICodeNode opNode = ICodeFactory.createICodeNode(nodeType);
            opNode.addChild(rootNode);
            token = nextToken(); // consume the operator
            // Parse another term. The operator node adopts
            // the term&apos;s tree as its second child.
            opNode.addChild(parseTerm(token));
            // The operator node becomes the new root node.
            rootNode = opNode;
            token = currentToken();
            tokenType = token.getType();
        }
        return rootNode;
    }

    private static final EnumSet<PascalTokenType> MULT_OPS =
            EnumSet.of(STAR, SLASH, DIV, PascalTokenType.MOD, PascalTokenType.AND);
    // Map multiplicative operator tokens to node types.
    private static final HashMap<PascalTokenType, ICodeNodeType>
            MULT_OPS_OPS_MAP = new HashMap<PascalTokenType, ICodeNodeType>();

    static {
        MULT_OPS_OPS_MAP.put(STAR, MULTIPLY);
        MULT_OPS_OPS_MAP.put(SLASH, FLOAT_DIVIDE);
        MULT_OPS_OPS_MAP.put(DIV, INTEGER_DIVIDE);
        MULT_OPS_OPS_MAP.put(PascalTokenType.MOD, ICodeNodeTypeImpl.MOD);
        MULT_OPS_OPS_MAP.put(PascalTokenType.AND, ICodeNodeTypeImpl.AND);
    }


    // term
    private ICodeNode parseTerm(Token token) throws Exception {
        // Parse a factor and make its node the root node.
        ICodeNode rootNode = parseFactor(token);
        token = currentToken();
        TokenType tokenType = token.getType();
        while (MULT_OPS.contains(tokenType)) {
            // Create a new operator node and adopt the current tree
            // as its first child.
            ICodeNodeType nodeType = MULT_OPS_OPS_MAP.get(tokenType);
            ICodeNode opNode = ICodeFactory.createICodeNode(nodeType);
            opNode.addChild(rootNode);
            token = nextToken(); // consume the operator
            // Parse another factor. The operator node adopts
            // the term&apos;s tree as its second child.
            opNode.addChild(parseFactor(token));
            // The operator node becomes the new root node.
            rootNode = opNode;
            token = currentToken();
            tokenType = token.getType();
        }
        return rootNode;
    }

    // factor
    private ICodeNode parseFactor(Token token) throws Exception {
        return null;
    }


}
