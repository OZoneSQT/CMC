package dk.seahawk.generator;

import dk.seahawk.checker.IChecker;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.utils.IErrorHandler;

public class Generator implements IGenerator {

    private IChecker checker;
    private IErrorHandler errorHandler;

    public Generator(IErrorHandler errorHandler) {
        this.checker = checker;
        this.errorHandler = errorHandler;
    }

    @Override
    public void generate(Program program) {

    }

}
