package wirte_a_compiler_with_java.intermediate.symtabimpl;

import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabStack;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 12:11 PM
 */
public class SymTabFactory {
    public static SymTabStack createSymTabStack()
    {
        return new SymTabStackImpl();

    }

    public static SymTab createSymTab(int nestingLevel)
    {
        return new SymTabImpl(nestingLevel);
    }

    public static SymTabEntry createSymTabEntry(String name, SymTab symTab)
    {
        return new SymTabEntryImpl(name, symTab);
    }
}
