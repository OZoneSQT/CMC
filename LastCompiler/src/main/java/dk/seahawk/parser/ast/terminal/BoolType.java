package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;

public class BoolType extends Terminal {

    // true or false
    public BoolType( String spelling ) {
        setSpelling(spelling);
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitBoolType( this, arg);
    }

}