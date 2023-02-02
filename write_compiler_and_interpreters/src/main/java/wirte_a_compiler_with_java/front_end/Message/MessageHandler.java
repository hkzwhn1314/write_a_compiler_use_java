package wirte_a_compiler_with_java.front_end.Message;

import wirte_a_compiler_with_java.front_end.inter.MessageListener;

import java.util.ArrayList;

/**
 * @Author zhaocenliu
 * @create 2023/1/30 11:51 PM
 */
public class MessageHandler {
    private Message message; // message
    private ArrayList<MessageListener> listeners; // listener list

    public MessageHandler() {
        this.listeners = new ArrayList<MessageListener>();
    }

    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    public void sendMessage(Message message) {
        this.message = message;
        notifyListeners();
    }

    private void notifyListeners() {
        for (MessageListener listener : listeners) {
            listener.messageReceived(message);
        }
    }
}


