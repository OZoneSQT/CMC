package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Operator;

public class UnaryExpression extends Expression {
    public Operator operator;
    public Expression operand;

    public UnaryExpression( Operator operator, Expression operand ) {
        this.operator = operator;
        this.operand = operand;
    }
}
