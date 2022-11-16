package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;

public class IntegerLiteral extends Terminal {

    public IntegerLiteral( String spelling ) {
        setSpelling(spelling);
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitIntegerLiteral( this, arg);
    }

}
