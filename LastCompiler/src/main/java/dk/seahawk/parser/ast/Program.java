package dk.seahawk.parser.ast;

import dk.seahawk.parser.ast.terminal.Block;

public class Program extends AST {
    private Block block;

    public Program() {}

    public Program(Block block) {
        this.block = block;
    }

    public Block getProgram() {
        return block;
    }

    public void setProgram(Block block) {
        this.block = block;
    }

    public Object visitProgram( Program program, Object arg ) {
        return program.visitProgram(this, arg);
    }

}
