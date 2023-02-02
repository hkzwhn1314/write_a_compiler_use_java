package wirte_a_compiler_with_java.front_end.Parse;

import wirte_a_compiler_with_java.front_end.token.Token;

/**
 * @Author zhaocenliu
 * @create 2023/1/26 12:44 PM
 */
public abstract class Scanner {

    protected Source source; // source
    private Token currentToken; // current token

    public Scanner(Source source) {
        this.source = source;
    }

    public Token currentToken() {
        return currentToken;
    }

    public Token nextToken() throws Exception {
        currentToken = extractToken();
        return currentToken;
    }

    protected abstract Token extractToken() throws Exception;

    public char currentChar() throws Exception {
        return source.currentChar();
    }

    public char nextChar() throws Exception {
        return source.nextChar();
    }
}
