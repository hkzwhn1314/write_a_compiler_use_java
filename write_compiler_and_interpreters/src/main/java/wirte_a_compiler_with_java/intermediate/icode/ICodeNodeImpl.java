package wirte_a_compiler_with_java.intermediate.icode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author zhaocenliu
 * @create 2023/2/5 12:09 PM
 */
public class ICodeNodeImpl extends HashMap<ICodeKey, Object> implements ICodeNode {
    private ICodeNodeType type; // node type
    private ICodeNode parent; // parent node
    private ArrayList<ICodeNode> children; // children array list

    public ICodeNodeImpl(ICodeNodeType type) {
        this.type = type;
        this.parent = null;
        this.children = new ArrayList<ICodeNode>();
    }

    @Override
    public ICodeNodeType getType() {
        return null;
    }

    @Override
    public ICodeNode getParent() {
        return null;
    }

    @Override
    public ICodeNode addChild(ICodeNode node) {
        return null;
    }

    @Override
    public ArrayList<ICodeNode> getChildren() {
        return null;
    }

    @Override
    public void setAttribute(ICodeKey key, Object value) {

    }

    @Override
    public Object getAttribute(ICodeKey key) {
        return null;
    }

    @Override
    public ICodeNode copy() {
        return null;
    }
}
