package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.terminal.Operator;

public class UnaryExpression extends Expression {
    private Operator operator;

    private Expression operand;

    public UnaryExpression( Operator operator, Expression operand ) {
        this.operator = operator;
        this.operand = operand;
    }

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

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitUnaryExpression(this, arg);
    }

}
