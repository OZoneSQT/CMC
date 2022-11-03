package dk.seahawk.generator;

import dk.seahawk.parser.ast.Program;

public interface IGenerator {
    void generate(Program program);

    void saveTargetProgram(String targetName);
}
