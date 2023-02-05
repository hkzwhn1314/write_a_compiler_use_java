package wirte_a_compiler_with_java.back_end.backendMain;

import wirte_a_compiler_with_java.front_end.Message.MessageHandler;
import wirte_a_compiler_with_java.intermediate.icode.ICode;
import wirte_a_compiler_with_java.front_end.inter.MessageProducer;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;


/**
 * @Author zhaocenliu
 * @create 2023/1/31 12:24 AM
 */
public abstract class Backend implements MessageProducer {
    // Message handler delegate.
    protected static MessageHandler messageHandler;
    static {
        messageHandler = new MessageHandler();
    }
    protected SymTab symTab; // symbol table
    protected ICode iCode; // intermediate code

    /**
     * Process the intermediate code and the symbol table generated by the
     * parser. To be implemented by a compiler or an interpreter subclass.
     * @param iCode the intermediate code.
     * @param symTab the symbol table.
     * @throws Exception if an error occurred.
     * 抽象 process() 方法需要引用中间代码和符号表。
     * 编译器将实现 process() 以生成目标代码，而解释器将实现 process() 以执行程序。
     */
    public abstract void process(ICode iCode, SymTab symTab) throws Exception;
}
