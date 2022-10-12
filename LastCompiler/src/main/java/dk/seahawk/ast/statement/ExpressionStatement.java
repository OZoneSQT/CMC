package dk.seahawk.ast.statement;

import dk.seahawk.ast.expression.Expression;

public class ExpressionStatement extends Statement {
    public Expression exp;

    public ExpressionStatement( Expression exp ) {
        this.exp = exp;
    }
}
