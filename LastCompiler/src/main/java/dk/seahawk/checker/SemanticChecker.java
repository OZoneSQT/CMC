package dk.seahawk.checker;

import dk.seahawk.parser.ast.Program;

public interface SemanticChecker {
    void check(Program program);
}
