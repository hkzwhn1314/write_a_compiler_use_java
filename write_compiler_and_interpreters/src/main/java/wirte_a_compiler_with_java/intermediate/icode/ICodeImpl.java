package wirte_a_compiler_with_java.intermediate.icode;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 12:09 PM
 */
public class ICodeImpl implements ICode{
    private ICodeNode root;
    @Override
    public ICodeNode setRoot(ICodeNode node) {
        root =  node;
        return node;
    }

    @Override
    public ICodeNode getRoot() {
        return null;
    }
}
