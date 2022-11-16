package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.terminal.IntegerLiteral;

public class IntegerExpression extends Expression {
    private IntegerLiteral name;

    public IntegerExpression(IntegerLiteral name ) {
        this.name = name;
    }

    public IntegerLiteral getName() {
        return name;
    }

    public void setName(IntegerLiteral name) {
        this.name = name;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitIntegerExpression(this, arg);
    }

}
