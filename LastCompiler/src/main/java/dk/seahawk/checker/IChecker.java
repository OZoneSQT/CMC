package dk.seahawk.checker;

import dk.seahawk.parser.IParser;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.utils.IErrorHandler;

public interface IChecker {
    void check(Program program);
}
