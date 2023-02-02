package wirte_a_compiler_with_java.front_end.pascal;

import wirte_a_compiler_with_java.front_end.Parse.Scanner;
import wirte_a_compiler_with_java.front_end.Parse.Source;
import wirte_a_compiler_with_java.front_end.pascal.tokens.*;
import wirte_a_compiler_with_java.front_end.token.EofToken;
import wirte_a_compiler_with_java.front_end.token.Token;

import static wirte_a_compiler_with_java.front_end.Parse.Source.EOF;
import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.INVALID_CHARACTER;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 12:37 PM
 */
public class PascalScanner extends Scanner {
    public PascalScanner(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws Exception {
        Token token;
        char currentChar = currentChar();
        skipWhiteSpace();
        // Construct the next token. The current character determines the
        // token type.
        if (currentChar == EOF) {
            token = new EofToken(source);
            // any define is not start with num for int 1n is invalid
        } else if (Character.isLetter(currentChar)) {
            token = new PascalWordToken(source);
        } else if (Character.isDigit(currentChar)) {
            token = new PascalNumberToken(source);
        } else if (currentChar == '\'') {
            token = new PascalStringToken(source);
        } else if (PascalTokenType.SPECIAL_SYMBOLS
                .containsKey(Character.toString(currentChar))) {
            token = new PascalSpecialSymbolToken(source);
        } else {
            token = new PascalErrorToken(source, INVALID_CHARACTER,
                    Character.toString(currentChar));
            nextChar(); // consume character
        }
        return token;

    }

    private void skipWhiteSpace() throws Exception {
        char currentChar = currentChar();
        while (Character.isWhitespace(currentChar) || currentChar == '{') {
            if (currentChar == '{') {
                do {
                    currentChar = nextChar(); // consume comment characters
                } while ((currentChar != '}') && (currentChar != EOF));
                // Found closing &apos;}&apos;?
                if (currentChar == '}') {
                    currentChar = nextChar(); // consume the &apos;}&apos;
                }
            }
            // Not a comment.
            else {
                currentChar = nextChar(); // consume whitespace character
            }
        }
    }
}


