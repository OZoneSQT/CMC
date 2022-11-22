package dk.seahawk.parser.ast.statement;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.expression.Expression;

public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement(Expression expression ) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitExpressionStatement( this, arg);
    }

}
