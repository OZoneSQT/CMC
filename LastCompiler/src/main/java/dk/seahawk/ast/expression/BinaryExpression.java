package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Operator;

public class BinaryExpression extends Expression {
    private Operator operator;
    private Expression operand1;
    private Expression operand2;

    public BinaryExpression( Operator operator, Expression operand1, Expression operand2 ) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }
}
