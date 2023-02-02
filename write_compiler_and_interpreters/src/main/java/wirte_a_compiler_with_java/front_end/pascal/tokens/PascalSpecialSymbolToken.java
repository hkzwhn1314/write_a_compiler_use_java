package wirte_a_compiler_with_java.front_end.pascal.tokens;

import wirte_a_compiler_with_java.front_end.Parse.Source;

import static wirte_a_compiler_with_java.front_end.pascal.PascalErrorCode.INVALID_CHARACTER;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.ERROR;
import static wirte_a_compiler_with_java.front_end.pascal.tokens.PascalTokenType.SPECIAL_SYMBOLS;

/**
 * @Author zhaocenliu
 * @create 2023/2/1 2:58 PM
 */
public class PascalSpecialSymbolToken extends PascalToken {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token&apos;s characters.
     * @throws Exception if an error occurred.
     */
    public PascalSpecialSymbolToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        char currentChar = currentChar();
        text = Character.toString(currentChar);
        type = null;

        switch (currentChar) {
            case '+':
            case '-':
            case '*':
            case '/':
            case ',':
            case ';':
            case '\'':
            case '=':
            case '(':
            case ')':
            case '[':
            case ']':
            case '{':
            case '}':
            case '^': {
                nextChar(); // consume character
                break;
            }

            case ':': {
                currentChar = nextChar(); // consume &apos;:&apos;;
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar(); // consume &apos;=&apos;
                }
                break;
            }

            // < or <= or <>
            case '<': {
                currentChar = nextChar(); // consume &apos;<&apos;;
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar(); // consume &apos;=&apos;
                } else if (currentChar == '>') {
                    text += currentChar;
                    nextChar(); // consume &apos;>&apos;
                }
                break;
            }

            case '>': {
                currentChar = nextChar(); // consume &apos;>&apos;;
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar(); // consume &apos;=&apos;
                }
                break;
            }

            case '.': {
                currentChar = nextChar(); // consume &apos;.&apos;;
                if (currentChar == '.') {
                    text += currentChar;
                    nextChar(); // consume &apos;.&apos;
                }
                break;
            }

            default: {
                nextChar(); // consume bad character
                type = ERROR;
                value = INVALID_CHARACTER;
            }
        }
        // Set the type if it wasn&apos;t an error.
        if (type == null) {
            type = SPECIAL_SYMBOLS.get(text);
        }

    }
}