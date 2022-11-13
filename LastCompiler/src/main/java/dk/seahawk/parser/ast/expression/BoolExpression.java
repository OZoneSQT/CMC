package dk.seahawk.parser.ast.expression;

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

    public Object visit(BoolExpression boolExpression, Object arg ) {
        return boolExpression.visit(this,arg);
    }

}
