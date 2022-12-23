package dk.seahawk.generator;

import dk.seahawk.parser.ast.Program;

public interface CodeGenerator {
    void generate(Program program);

    void saveTargetProgram(String targetName);
}
