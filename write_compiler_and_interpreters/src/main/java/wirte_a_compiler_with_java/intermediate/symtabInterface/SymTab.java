package wirte_a_compiler_with_java.intermediate.symtabInterface;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 12:27 PM
 */
public interface SymTab {
    public int getNestingLevel();
    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry enter(String name);
    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry lookup(String name);
    public ArrayList<wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry> sortedEntries();
}
