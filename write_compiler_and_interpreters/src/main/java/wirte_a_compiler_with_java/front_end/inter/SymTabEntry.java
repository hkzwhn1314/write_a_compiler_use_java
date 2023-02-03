package wirte_a_compiler_with_java.front_end.inter;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 11:58 AM
 */
public interface SymTabEntry {
    public String getName();
    public SymTab getSymTab();
    public void appendLineNumber(int lineNumber);
    public ArrayList<Integer> getLineNumbers();
    public void setAttribute(SymTabKey key, Object value);
    public Object getAttribute(SymTabKey key);
}
