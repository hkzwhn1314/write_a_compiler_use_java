package wirte_a_compiler_with_java.back_end.compiler;

import wirte_a_compiler_with_java.back_end.backendMain.Backend;
import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.front_end.inter.ICode;
import wirte_a_compiler_with_java.front_end.inter.MessageListener;
import wirte_a_compiler_with_java.intermediate.symtabInterface.SymTab;


import static wirte_a_compiler_with_java.front_end.Message.MessageType.COMPILER_SUMMARY;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 1:03 PM
 */
public class CodeGenerator extends Backend {
    @Override
    public void process(ICode iCode, SymTab symTab) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        int instructionCount = 0;
        // Send the compiler summary message.
        sendMessage(new Message(COMPILER_SUMMARY,
                new Number[]{instructionCount,
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
