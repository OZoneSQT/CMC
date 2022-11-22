package dk.seahawk.parser.ast;

import dk.seahawk.checker.IVisitor;

public abstract class AST {
    public abstract Object visit(IVisitor v, Object arg );
}
