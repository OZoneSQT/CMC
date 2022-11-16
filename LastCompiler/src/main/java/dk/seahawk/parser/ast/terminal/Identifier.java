package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;

public class Identifier extends Terminal {
    public Identifier( String spelling ) {
        this.setSpelling(spelling);
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitIdentifier( this, arg);
    }

}
