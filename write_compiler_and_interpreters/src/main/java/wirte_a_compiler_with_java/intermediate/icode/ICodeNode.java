package wirte_a_compiler_with_java.intermediate.icode;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 11:59 AM
 */
// use ast tree
public interface ICodeNode {
    public ICodeNodeType getType();
    public ICodeNode getParent();
    public ICodeNode addChild(ICodeNode node);
    public ArrayList<ICodeNode> getChildren();
    public void setAttribute(ICodeKey key, Object value);
    public Object getAttribute(ICodeKey key);
    public ICodeNode copy();
}
