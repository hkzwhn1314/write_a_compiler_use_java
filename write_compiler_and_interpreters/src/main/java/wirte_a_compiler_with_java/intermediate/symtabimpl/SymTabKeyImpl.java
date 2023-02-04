package wirte_a_compiler_with_java.intermediate.symtabimpl;

import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabKey;

/**
 * @Author zhaocenliu
 * @create 2023/2/3 3:44 PM
 */

public enum SymTabKeyImpl implements SymTabKey {
    // Constant.
    CONSTANT_VALUE,
    // Procedure or function.
    ROUTINE_CODE, ROUTINE_SYMTAB, ROUTINE_ICODE,
    ROUTINE_PARMS, ROUTINE_ROUTINES,
    // Variable or record field value.
    DATA_VALUE
}
