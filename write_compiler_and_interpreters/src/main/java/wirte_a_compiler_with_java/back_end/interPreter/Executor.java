package wirte_a_compiler_with_java.back_end.interPreter;

import wirte_a_compiler_with_java.back_end.backendMain.Backend;
import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.intermediate.icode.ICode;
import wirte_a_compiler_with_java.front_end.inter.MessageListener;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;


import static wirte_a_compiler_with_java.front_end.Message.MessageType.INTERPRETER_SUMMARY;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 1:43 PM
 */
public class Executor extends Backend {
    @Override
    public void process(ICode iCode, SymTab symTab) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        int executionCount = 0;
        int runtimeErrors = 0;
        // Send the interpreter summary message.
        sendMessage(new Message(INTERPRETER_SUMMARY,
                new Number[]{executionCount,
                        runtimeErrors,
                        elapsedTime}));
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    @Override
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }
}
