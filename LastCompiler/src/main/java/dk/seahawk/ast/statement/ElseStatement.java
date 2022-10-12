package dk.seahawk.ast.statement;

import dk.seahawk.ast.expression.Expression;

public class ElseStatement extends Statement {
    public Expression exp;
    public StatementList thenPart;
    public StatementList elsePart;

    public ElseStatement( Expression exp, StatementList thenPart, StatementList elsePart ) {
        this.exp = exp;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }
}
