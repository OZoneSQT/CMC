package dk.seahawk.parser.ast.statement;

import dk.seahawk.parser.ast.expression.Expression;

public class IfStatement extends Statement {
    private Expression exp;
    private StatementList thenPart;
    private StatementList elsePart;

    public IfStatement(Expression exp, StatementList thenPart, StatementList elsePart ) {
        this.exp = exp;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    public StatementList getThenPart() {
        return thenPart;
    }

    public StatementList getElsePart() {
        return elsePart;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public void setThenPart(StatementList thenPart) {
        this.thenPart = thenPart;
    }

    public void setElsePart(StatementList elsePart) {
        this.elsePart = elsePart;
    }
}
