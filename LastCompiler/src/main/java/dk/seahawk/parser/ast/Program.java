package dk.seahawk.parser.ast;

import dk.seahawk.checker.Checker;
import dk.seahawk.checker.IVisitor;
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

    @Override
    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitProgram(this, arg);
    }
}
