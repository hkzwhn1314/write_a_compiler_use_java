package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;

import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.MISSING_END;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.END;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl.COMPOUND;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 3:38 PM
 */
public class CompoundStatementParser extends StatementParser {
    public CompoundStatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        token = nextToken(); // consume the BEGIN
        // Create the COMPOUND node.
        ICodeNode compoundNode = ICodeFactory.createICodeNode(COMPOUND);
        // Parse the statement list terminated by the END token.
        StatementParser statementParser = new StatementParser(this);
        statementParser.parseList(token, compoundNode, END, MISSING_END);
        return compoundNode;
    }
}
