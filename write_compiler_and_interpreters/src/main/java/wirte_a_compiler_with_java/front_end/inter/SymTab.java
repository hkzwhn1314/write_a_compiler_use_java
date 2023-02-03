package wirte_a_compiler_with_java.front_end.inter;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 12:27 PM
 */
public interface SymTab {
    public int getNestingLevel();
    public SymTabEntry enter(String name);
    public SymTabEntry lookup(String name);
    public ArrayList<SymTabEntry> sortedEntries();
}
