package wirte_a_compiler_with_java.intermediate.symtabInterface;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 11:58 AM
 */
public interface SymTabEntry {
    public String getName();
    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab getSymTab();
    public void appendLineNumber(int lineNumber);
    public ArrayList<Integer> getLineNumbers();
    public void setAttribute(wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabKey key, Object value);
    public Object getAttribute(wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabKey key);
}
