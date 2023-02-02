package wirte_a_compiler_with_java.front_end.pascal.tokens;

import wirte_a_compiler_with_java.front_end.Parse.Source;

import static wirte_a_compiler_with_java.front_end.Parse.Source.EOF;
import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.UNEXPECTED_EOF;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.ERROR;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.STRING;

/**
 * @Author zhaocenliu
 * @create 2023/2/1 2:58 PM
 */
public class PascalStringToken extends PascalToken {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public PascalStringToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();
        char currentChar = nextChar(); // consume initial quote
        textBuffer.append('\'');

        // Get string characters.
        do {
            // Replace any whitespace character with a blank.
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }
            if ((currentChar != '\'') && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar(); // consume character
            }
            // Quote? Each pair of adjacent quotes represents a single-quote.
            if (currentChar == '\'') {
                while ((currentChar == '\'') && (peekChar() == '\'')) {
                    textBuffer.append("'");
                    valueBuffer.append(currentChar); // append single-quote
                    currentChar = nextChar(); // consume pair of quotes
                    currentChar = nextChar();
                }
            }
        } while ((currentChar != '\'') && (currentChar != EOF));

        if (currentChar == '\'') {
            nextChar(); // consume final quote
            textBuffer.append('\'');
            type = STRING;
            value = valueBuffer.toString();
        } else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }
        text = textBuffer.toString();
    }


}
