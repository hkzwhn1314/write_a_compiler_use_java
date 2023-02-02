package wirte_a_compiler_with_java.front_end.token;

import wirte_a_compiler_with_java.front_end.Parse.Source;
import wirte_a_compiler_with_java.front_end.inter.TokenType;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 12:46 PM
 */
public class Token {
    protected TokenType type; // language-specific token type
    protected String text; // token text
    protected Object value; // token value
    protected Source source; // source
    protected int lineNum; // line number of the token&apos;s source line
    protected int position; // position of the first token character

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }


    public Object getValue() {
        return value;
    }

    public int getLineNumber() {
        return lineNum;
    }

    public int getPosition() {
        return position;
    }
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNum = source.getLineNum();
        this.position = source.getPosition();
        extract();
    }

    /**
     * Default method to extract only one-character tokens from the source.
     * Subclasses can override this method to construct language-specific
     * tokens. After extracting the token, the current source line position
     * will be one beyond the last token character.
     *
     * @throws Exception if an error occurred.
     */
    protected void extract() throws Exception {
        text = Character.toString(currentChar());
        value = null;
        nextChar(); // consume current character
    }

    /**
     * Call the source&apos;s currentChar() method.
     *
     * @return the current character from the source.
     * @throws Exception if an error occurred.
     */
    protected char currentChar() throws Exception {
        return source.currentChar();
    }

    /**
     * Call the source&apos;s nextChar() method.
     *
     * @return the next character from the source after moving forward.
     * @throws Exception if an error occurred.
     */
    protected char nextChar() throws Exception {
        return source.nextChar();
    }

    /**
     * Call the source&apos;s peekChar() method.
     *
     * @return the next character from the source without moving forward.
     * @throws Exception if an error occurred.
     */
    protected char peekChar() throws Exception {
        return source.peekChar();
    }


}
