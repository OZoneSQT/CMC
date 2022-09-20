package dk.seahawk.generator;

import dk.seahawk.checker.IChecker;
import dk.seahawk.utils.IErrorHandler;

public class Generator implements IGenerator {

    private IChecker checker;
    private IErrorHandler errorHandler;

    public Generator(IChecker checker, IErrorHandler errorHandler) {
        this.checker = checker;
        this.errorHandler = errorHandler;
    }

    @Override
    public void generate() {

    }

}
