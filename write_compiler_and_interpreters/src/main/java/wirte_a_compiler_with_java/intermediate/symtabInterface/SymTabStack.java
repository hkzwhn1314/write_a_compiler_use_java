package wirte_a_compiler_with_java.intermediate.symtabInterface;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 11:55 AM
 */
public interface SymTabStack {
    // return the current nesting level.
    public int getCurrentNestingLevel();

    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab getLocalSymTab();

    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry enterLocal(String name);

    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry lookupLocal(String name);

    public wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry lookup(String name);
}
