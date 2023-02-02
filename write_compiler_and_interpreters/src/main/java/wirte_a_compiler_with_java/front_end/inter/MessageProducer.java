package wirte_a_compiler_with_java.front_end.inter;

import wirte_a_compiler_with_java.front_end.Message.Message;


/**
 * @Author zhaocenliu
 * @create 2023/1/30 10:30 PM
 */
public interface MessageProducer {
    /**
     * Add a listener to the listener list.
     *
     * @param listener the listener to add.
     */
    public void addMessageListener(MessageListener listener);

    /**
     * Remove a listener from the listener list.
     *
     * @param listener the listener to remove.
     */


    /**
     * Notify listeners after setting the message.
     *
     * @param message the message to set.
     */
    public void sendMessage(Message message);
}
