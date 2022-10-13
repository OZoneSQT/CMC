package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Operator;

public class UnaryExpression extends Expression {
    private Operator operator;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }

    private Expression operand;

    public UnaryExpression( Operator operator, Expression operand ) {
        this.operator = operator;
        this.operand = operand;
    }
}
