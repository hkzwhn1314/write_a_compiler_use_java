package wirte_a_compiler_with_java.front_end.Message;

/**
 * @Author zhaocenliu
 * @create 2023/1/30 11:09 PM
 */
public class Message {
    private MessageType type;
    private Object body;

    public MessageType getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }

    /**
     * Constructor.
     *
     * @param type the message type.
     * @param body the message body.
     */
    public Message(MessageType type, Object body) {
        this.type = type;
        this.body = body;
    }

}
