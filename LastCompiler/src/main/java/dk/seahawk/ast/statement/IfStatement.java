package dk.seahawk.ast.statement;

import dk.seahawk.ast.expression.Expression;

public class IfStatement extends Statement {
    public Expression exp;
    public StatementList thenPart;
    public StatementList elsePart;

    public IfStatement( Expression exp, StatementList thenPart, StatementList elsePart ) {
        this.exp = exp;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }
}
