package wirte_a_compiler_with_java.front_end.inter;

import wirte_a_compiler_with_java.front_end.Message.Message;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 12:06 AM
 */
public interface MessageListener {

    public void messageReceived(Message message);

}
