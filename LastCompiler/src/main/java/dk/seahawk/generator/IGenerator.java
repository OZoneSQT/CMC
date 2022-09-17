package dk.seahawk.generator;

import dk.seahawk.checker.IChecker;

public interface IGenerator {
    void init(IChecker checker);
    void generateProgram();
}
