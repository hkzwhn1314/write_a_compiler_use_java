package wirte_a_compiler_with_java.front_end.pascal.tokens;

import wirte_a_compiler_with_java.front_end.Parse.Source;
import wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode;
import wirte_a_compiler_with_java.front_end.pascal.tokens.PascalToken;

import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.ERROR;

/**
 * @Author zhaocenliu
 * @create 2023/2/1 1:38 PM
 */
public class PascalErrorToken extends PascalToken {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public PascalErrorToken(Source source, PascalErrorCode errorCode,
                            String tokenText) throws Exception {
        super(source);
        this.text = tokenText;
        this.type = ERROR;
        this.value = errorCode;
    }

    /**
     * Do nothing. Do not consume any source characters.
     *
     * @throws Exception if an error occurred.
     */
    protected void extract() throws Exception {
    }

}
