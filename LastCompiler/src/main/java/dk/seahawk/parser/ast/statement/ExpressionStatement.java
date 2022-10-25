package dk.seahawk.parser.ast.statement;

import dk.seahawk.parser.ast.expression.Expression;

public class ExpressionStatement extends Statement {
    private Expression exp;

    public ExpressionStatement(Expression exp ) {
        this.exp = exp;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg ) {
        return expressionStatement.visitExpressionStatement(this,arg);
    }

}
