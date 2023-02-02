package wirte_a_compiler_with_java.front_end.token;

import wirte_a_compiler_with_java.front_end.Parse.Source;
import wirte_a_compiler_with_java.front_end.token.Token;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 4:41 PM
 */
public class EofToken extends Token {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public EofToken(Source source) throws Exception {
        super(source);
    }

    protected void extract(Source source) throws Exception {
    }

}
// p 26