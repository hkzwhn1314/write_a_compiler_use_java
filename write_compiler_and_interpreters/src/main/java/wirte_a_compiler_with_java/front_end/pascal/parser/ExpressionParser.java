package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.inter.TokenType;
import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNodeType;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry;

import java.util.EnumSet;
import java.util.HashMap;

import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.*;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.*;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeKeyImpl.ID;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeKeyImpl.VALUE;
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
        TokenType tokenType = token.getType();
        ICodeNode rootNode = null;
        switch ((PascalTokenType) tokenType) {
            // analysis all the kind of solutions
            case IDENTIFIER: {
                // Look up the identifier in the symbol table stack.
                // Flag the identifier as undefined if it&apos;s not found.
                String name = token.getText().toLowerCase();
                SymTabEntry id = symTabStack.lookup(name);
                if (id == null) {
                    errorHandler.flag(token, IDENTIFIER_UNDEFINED, this);
                    id = symTabStack.enterLocal(name);
                }
                rootNode = ICodeFactory.createICodeNode(VARIABLE);
                rootNode.setAttribute(ID, id);
                id.appendLineNumber(token.getLineNumber());
                token = nextToken(); // consume the identifier
                break;
            }
            case INTEGER: {
                rootNode = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
                rootNode.setAttribute(VALUE, token.getValue());
                token = nextToken();
                break;
            }
            case REAL: {
                // Create an REAL_CONSTANT node as the root node.
                rootNode = ICodeFactory.createICodeNode(REAL_CONSTANT);
                rootNode.setAttribute(VALUE, token.getValue());
                token = nextToken();
                break;
            }
            case STRING: {
                String value = (String) token.getValue();
                // Create a STRING_CONSTANT node as the root node.
                rootNode = ICodeFactory.createICodeNode(STRING_CONSTANT);
                rootNode.setAttribute(VALUE, value);
                token = nextToken(); // consume the string
                break;
            }
            case NOT: {
                token = nextToken(); // consume the NOT
                // Create a NOT node as the root node.
                rootNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.NOT);
                // Parse the factor. The NOT node adopts the
                // factor node as its child.
                rootNode.addChild(parseFactor(token));
                break;
            }
            case LEFT_PAREN: {
                token = nextToken(); // consume the (
                // Parse an expression and make its node the root node.
                rootNode = parseExpression(token);
                // Look for the matching ) token.
                token = currentToken();
                if (token.getType() == RIGHT_PAREN) {
                    token = nextToken(); // consume the )
                } else {
                    errorHandler.flag(token, MISSING_RIGHT_PAREN, this);
                }
                break;
            }
            default: {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
                break;
            }

        }
        return rootNode;
    }


}
