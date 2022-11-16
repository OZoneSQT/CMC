package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.terminal.BoolType;

public class BoolExpression extends Expression {
    private BoolType name;

    public BoolExpression(BoolType name ) {
        this.name = name;
    }

    public BoolType getName() {
        return name;
    }

    public void setName(BoolType name) {
        this.name = name;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitBoolExpression(this,arg);
    }

}
