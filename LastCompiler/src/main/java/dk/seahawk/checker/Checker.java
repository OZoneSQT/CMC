package dk.seahawk.checker;

import dk.seahawk.parser.IParser;
import dk.seahawk.utils.IErrorHandler;

public class Checker implements IChecker {

    private IParser parser;
    private IErrorHandler errorHandler;

    public Checker(IParser parser, IErrorHandler errorHandler) {
        this.parser = parser;
        this.errorHandler = errorHandler;
    }

    @Override
    public void check() {

    }

}
