package wirte_a_compiler_with_java.intermediate.icode;

import java.util.*;

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
        if (node != null) {
            children.add(node);
            ((ICodeNodeImpl) node).parent = this;
        }
        return node;
    }

    @Override
    public ArrayList<ICodeNode> getChildren() {
        return null;
    }

    @Override
    public void setAttribute(ICodeKey key, Object value) {
        put(key, value);
    }

    @Override
    public Object getAttribute(ICodeKey key) {
        return get(key);
    }

    @Override
    public ICodeNode copy() {
        // Create a copy with the same type.
        ICodeNodeImpl copy = (ICodeNodeImpl) ICodeFactory.createICodeNode(type);
        Set<Entry<ICodeKey, Object>> attributes = entrySet();
        Iterator<Entry<ICodeKey, Object>> it = attributes.iterator();
        // Copy attributes
        while (it.hasNext()) {
            Map.Entry<ICodeKey, Object> attribute = it.next();
            copy.put(attribute.getKey(), attribute.getValue());
        }
        return copy;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
