package wirte_a_compiler_with_java.intermediate.icode;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 12:08 PM
 */
public class ICodeFactory {
    /**
     * Create and return an intermediate code implementation.
     * @return the intermediate code implementation.
     */
    public static ICode createICode()
    {
        return new ICodeImpl();
    }
    /**
     * Create and return a node implementation.
     * @param type the node type.
     * @return the node implementation.
     */
    public static ICodeNode createICodeNode(ICodeNodeType type)
    {
        return new ICodeNodeImpl(type);
    }
}
