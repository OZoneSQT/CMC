package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;

public class Operator extends Terminal {
    public Operator( String spelling ) {
        this.setSpelling(spelling);
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitOperator( this, arg);
    }

}
