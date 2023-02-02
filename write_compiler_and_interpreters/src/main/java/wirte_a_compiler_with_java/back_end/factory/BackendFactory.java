package wirte_a_compiler_with_java.back_end.factory;

import wirte_a_compiler_with_java.back_end.backendMain.Backend;
import wirte_a_compiler_with_java.back_end.compiler.CodeGenerator;
import wirte_a_compiler_with_java.back_end.interPreter.Executor;

/**
 * @Author zhaocenliu
 * @create 2023/1/31 1:46 PM
 */
public class BackendFactory {
    /**
     * Create a compiler or an interpreter back end component.
     *
     * @param operation either "compile" or "execute"
     * @return a compiler or an interpreter back end component.
     * @throws Exception if an error occurred.
     */
    public static Backend createBackend(String operation) throws Exception {
        if (operation.equalsIgnoreCase("compile")) {
            return new CodeGenerator();
        } else if (operation.equalsIgnoreCase("execute")) {
            return new Executor();
        } else {
            throw new Exception("Backend factory: Invalid operation &apos;" +
                    operation + "&apos;");
        }
    }
}
