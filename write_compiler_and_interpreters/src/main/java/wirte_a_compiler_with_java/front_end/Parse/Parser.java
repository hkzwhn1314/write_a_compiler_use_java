package wirte_a_compiler_with_java.front_end.Parse;

import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.front_end.Message.MessageHandler;

import wirte_a_compiler_with_java.front_end.inter.MessageListener;
import wirte_a_compiler_with_java.front_end.inter.MessageProducer;
import wirte_a_compiler_with_java.front_end.token.Token;

import wirte_a_compiler_with_java.front_end.inter.ICode;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTabStack;
import wirte_a_compiler_with_java.intermediate.symtabimpl.SymTabFactory;


/**
 * @Author zhaocenliu
 * @create 2023/1/26 12:25 PM
 */
public abstract class Parser implements MessageProducer {
    protected static SymTab symTab;
    protected static SymTabStack symTabStack;
    protected static MessageHandler messageHandler;
    protected Scanner scanner;
    protected ICode iCode; // 中间代码生成

    static {
        symTabStack = SymTabFactory.createSymTabStack();
        symTab = null;
        messageHandler = new MessageHandler();
    }

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    /**
     * Remove a parser message listener.
     *
     * @param listener the message listener to remove.
     */
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    /**
     * Notify listeners after setting the message.
     *
     * @param message the message to set.
     */
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

    protected Parser(Scanner scanner) {
        this.scanner = scanner;
        this.iCode = null;
    }

    public static SymTab getSymTab() {
        return symTab;
    }

    public ICode getICode() {
        return iCode;
    }

    public abstract void parse() throws Exception;

    public abstract int getErrorCount();

    public Token currentToken() {
        return scanner.currentToken();
    }

    public Token nextToken() throws Exception {
        return scanner.nextToken();
    }

    public SymTabStack getSymTabStack() {
        return symTabStack;
    }
}
