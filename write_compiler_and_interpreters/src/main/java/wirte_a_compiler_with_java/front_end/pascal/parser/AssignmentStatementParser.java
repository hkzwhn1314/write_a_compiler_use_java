package wirte_a_compiler_with_java.front_end.pascal.parser;

import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.token.Token;
import wirte_a_compiler_with_java.intermediate.icode.ICodeFactory;
import wirte_a_compiler_with_java.intermediate.icode.ICodeNode;

import static wirte_a_compiler_with_java.intermediate.icode.ICodeNodeTypeImpl.ASSIGN;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 3:41 PM
 */
public class AssignmentStatementParser extends StatementParser {
    public AssignmentStatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception{
        ICodeNode assignNode = ICodeFactory.createICodeNode(ASSIGN);
        String targetName = token.getText().toLowerCase();
        return null;
    }
}
