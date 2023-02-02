package wirte_a_compiler_with_java.front_end.pascal.tokens;

import wirte_a_compiler_with_java.front_end.Parse.Source;

import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.IDENTIFIER;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.RESERVED_WORDS;

/**
 * @Author zhaocenliu
 * @create 2023/2/1 2:57 PM
 */
public class PascalWordToken extends PascalToken {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public PascalWordToken(Source source) throws Exception {
        super(source);
    }


    protected void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();
        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar(); // consume character
        }
        text = textBuffer.toString();
        // Is it a reserved word or an identifier?
        type = (RESERVED_WORDS.contains(text.toLowerCase()))
                ? PascalTokenType.valueOf(text.toUpperCase()) // reserved word
                : IDENTIFIER;
    }
}
