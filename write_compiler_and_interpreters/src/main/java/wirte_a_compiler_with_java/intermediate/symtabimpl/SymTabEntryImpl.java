package wirte_a_compiler_with_java.intermediate.symtabimpl;

import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabEntry;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabKey;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 3:38 PM
 */

// 存储各种属性HashMap
public class SymTabEntryImpl extends HashMap<SymTabKey, Object> implements SymTabEntry {
    private String name; // entry name
    private SymTab symTab; // parent symbol table
    private ArrayList<Integer> lineNumbers; // source line numbers

    public SymTabEntryImpl(String name, SymTab symTab) {
        this.name = name;
        this.symTab = symTab;
        this.lineNumbers = new ArrayList<Integer>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SymTab getSymTab() {
        return symTab;
    }

    @Override
    public void appendLineNumber(int lineNumber) {
        lineNumbers.add(lineNumber);
    }

    @Override
    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }

    @Override
    public void setAttribute(SymTabKey key, Object value) {
        put(key, value);
    }

    @Override
    public Object getAttribute(SymTabKey key) {
        return get(key);
    }
}
