package dk.seahawk.ast.statement;

import dk.seahawk.ast.expression.Expression;

public class PrintStatement extends Statement {
    public Expression exp;

    public PrintStatement( Expression exp )
    {
        this.exp = exp;
    }
}
