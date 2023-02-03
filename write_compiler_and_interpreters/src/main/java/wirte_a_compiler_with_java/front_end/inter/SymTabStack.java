package wirte_a_compiler_with_java.front_end.inter;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 11:55 AM
 */
public interface SymTabStack {
    // return the current nesting level.
    public int getCurrentNestingLevel();

    public SymTab getLocalSymTab();

    public SymTabEntry enterLocal(String name);

    public SymTabEntry lookupLocal(String name);

    public SymTabEntry lookup(String name);
}
