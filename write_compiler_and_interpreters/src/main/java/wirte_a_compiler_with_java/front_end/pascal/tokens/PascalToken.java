package wirte_a_compiler_with_java.front_end.pascal.tokens;

import wirte_a_compiler_with_java.front_end.Parse.Scanner;
import wirte_a_compiler_with_java.front_end.Parse.Source;
import wirte_a_compiler_with_java.front_end.token.Token;

/**
 * @Author zhaocenliu
 * @create 2023/2/1 1:40 PM
 */
public class PascalToken extends Token {

    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public PascalToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
    }
}
