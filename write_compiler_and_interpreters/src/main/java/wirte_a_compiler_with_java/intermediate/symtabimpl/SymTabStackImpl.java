package wirte_a_compiler_with_java.intermediate.symtabimpl;

import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabStack;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 3:23 PM
 */

// 符号表栈的实现
public class SymTabStackImpl extends ArrayList<SymTab> implements SymTabStack {
    private int currentNestingLevel;

    public SymTabStackImpl() {
        // is always = zero
        this.currentNestingLevel = 0;
        add(SymTabFactory.createSymTab(currentNestingLevel));
    }

    @Override
    // TODO
    public int getCurrentNestingLevel() {
        return currentNestingLevel;
    }

    @Override
    public SymTab getLocalSymTab() {
        return get(currentNestingLevel);
    }

    @Override
    public SymTabEntry enterLocal(String name) {
        return get(currentNestingLevel).enter(name);
    }

    @Override
    public SymTabEntry lookupLocal(String name) {
        return get(currentNestingLevel).lookup(name);
    }

    @Override
    public SymTabEntry lookup(String name) {
        return lookupLocal(name);
    }
}
