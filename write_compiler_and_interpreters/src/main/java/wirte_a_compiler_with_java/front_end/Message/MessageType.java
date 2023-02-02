package wirte_a_compiler_with_java.front_end.Message;

/**
 * @Author zhaocenliu
 * @create 2023/1/30 11:48 PM
 */
public enum MessageType {
    SOURCE_LINE, SYNTAX_ERROR,
    PARSER_SUMMARY, INTERPRETER_SUMMARY, COMPILER_SUMMARY,
    MISCELLANEOUS, TOKEN,
    ASSIGN, FETCH, BREAKPOINT, RUNTIME_ERROR,
    CALL, RETURN,
}
