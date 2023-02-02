package wirte_a_compiler_with_java.front_end.Parse;

import wirte_a_compiler_with_java.front_end.Message.Message;
import wirte_a_compiler_with_java.front_end.inter.MessageListener;
import wirte_a_compiler_with_java.front_end.inter.MessageProducer;

import java.io.BufferedReader;
import java.io.IOException;

import static wirte_a_compiler_with_java.front_end.Message.MessageType.SOURCE_LINE;
import static wirte_a_compiler_with_java.front_end.Parse.Parser.messageHandler;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 3:01 PM
 */
public class Source implements MessageProducer {
    public static final char EOL = '\n';  // end-of-line character
    public static final char EOF = (char) 0; // end-of-file character
    private BufferedReader reader; // read for the source program
    private String line; // source line
    private int lineNum; // current source line number
    private int currentPos; // current source line position


    public Source(BufferedReader reader) throws IOException {
        this.lineNum = 0;
        this.currentPos = -2; // set to -2 to read the first source line
        this.reader = reader;
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getPosition() {
        return currentPos;
    }

    /**
     * Return the source character at the current position.
     *
     * @return the source character at the current position.
     * @throws Exception if an error occurred.
     */
    public char currentChar() throws Exception {
        // First time?
        if (currentPos == -2) {
            readLine();
            return nextChar();
        }
        // At end of file?
        else if (line == null) {
            return EOF;
        }
        // At end of line?
        else if ((currentPos == -1) || (currentPos == line.length())) {
            return EOL;
        }
        // Need to read the next line?
        else if (currentPos > line.length()) {
            readLine();
            return nextChar();
        }
        // Return the character at the current position.
        else {
            return line.charAt(currentPos);
        }
    }


    public char nextChar() throws Exception {
        ++currentPos;
        return currentChar();
    }

    public char peekChar() throws Exception {
        currentChar();
        if (line == null) {
            return EOF;
        }
        int nextPos = currentPos + 1;
        return nextPos < line.length() ? line.charAt(nextPos) : EOL;
    }

    private void readLine() throws IOException {
        line = reader.readLine(); // null when at the end of the source
        currentPos = -1;
        if (line != null) {
            ++lineNum;
            // TODO
            sendMessage(new Message(SOURCE_LINE,
                    new Object[]{lineNum, line}));
        }

    }

    public void close() throws Exception {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }


    @Override
    public void addMessageListener(MessageListener listener) {
    }

    @Override
    public void sendMessage(Message message) {

    }
}
