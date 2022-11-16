package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.terminal.CharLiteral;

public class CharExpression extends Expression {
    private CharLiteral name;

    public CharExpression(CharLiteral name ) {
        this.name = name;
    }

    public CharLiteral getName() {
        return name;
    }

    public void setName(CharLiteral name) {
        this.name = name;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitCharExpression(this,arg);
    }

}
