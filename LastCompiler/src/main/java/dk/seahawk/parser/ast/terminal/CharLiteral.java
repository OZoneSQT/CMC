package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;

public class CharLiteral extends Terminal {

    public CharLiteral( String spelling ) {
        setSpelling(spelling);
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitCharLiteral( this, arg);
    }

}