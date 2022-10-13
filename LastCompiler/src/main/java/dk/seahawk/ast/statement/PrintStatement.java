package dk.seahawk.ast.statement;

import dk.seahawk.ast.expression.Expression;

public class PrintStatement extends Statement {
    private Expression exp;

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public PrintStatement(Expression exp )
    {
        this.exp = exp;
    }
}
