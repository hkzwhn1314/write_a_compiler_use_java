package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry;

import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.MISSING_COLON_EQUALS;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.COLON_EQUALS;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeKeyImpl.ID;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl.ASSIGN;
import static wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl.VARIABLE;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 3:41 PM
 */
public class AssignmentStatementParser extends StatementParser {
    public AssignmentStatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        ICodeNode assignNode = ICodeFactory.createICodeNode(ASSIGN);
        String targetName = token.getText().toLowerCase();
        SymTabEntry targetId = symTabStack.lookup(targetName);
        if (targetId == null) {
            targetId = symTabStack.enterLocal(targetName);
        }
        targetId.appendLineNumber(token.getLineNumber());
        token = nextToken();
        ICodeNode variableNode = ICodeFactory.createICodeNode(VARIABLE);
        variableNode.setAttribute(ID, targetId);
        assignNode.addChild(variableNode);
        // Look for the := token.
        if (token.getType() == COLON_EQUALS) {
            token = nextToken(); // consume the :=
        } else {
            errorHandler.flag(token, MISSING_COLON_EQUALS, this);
        }
        // 解析表达式
        ExpressionParser expressionParser = new ExpressionParser(this);
        assignNode.addChild(expressionParser.parse(token));
        return assignNode;

    }
}
