package wirte_a_compiler_with_java.front_end.factory;

import wirte_a_compiler_with_java.front_end.Parse.Parser;
import wirte_a_compiler_with_java.front_end.Parse.Scanner;
import wirte_a_compiler_with_java.front_end.Parse.Source;
import wirte_a_compiler_with_java.front_end.pascal.PascalParserTD;
import wirte_a_compiler_with_java.front_end.pascal.PascalScanner;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 12:47 PM
 */

public class FrontendFactory {
    /**
     * Create a parser.
     *
     * @param language the name of the source language (e.g., "Pascal").
     * @param type     the type of parser (e.g., "top-down").
     * @param source   the source object.
     * @return the parser.
     * @throws Exception if an error occurred.
     */
    public static Parser createParser(String language, String type,
                                      Source source) throws Exception {
        if (language.equalsIgnoreCase("Pascal") &&
                type.equalsIgnoreCase("top-down")) {
            Scanner scanner = new PascalScanner(source);
            return new PascalParserTD(scanner);
        } else if (!language.equalsIgnoreCase("Pascal")) {
            throw new Exception("Parser factory: Invalid language &apos;" +
                    language + "&apos;");
        } else {
            throw new Exception("Parser factory: Invalid type &apos;" +
                    type + "&apos;");
        }
    }
}
