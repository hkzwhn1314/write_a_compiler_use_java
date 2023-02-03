package wirte_a_compiler_with_java.intermediate.symtabInterface;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 12:27 PM
 */
public interface SymTab {
    // 嵌套级别
    public int getNestingLevel();
    public SymTabEntry enter(String name);
    public SymTabEntry lookup(String name);
    public ArrayList<SymTabEntry> sortedEntries();
}
