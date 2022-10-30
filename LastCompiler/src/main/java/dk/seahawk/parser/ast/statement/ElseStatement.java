package dk.seahawk.parser.ast.statement;

import dk.seahawk.parser.ast.expression.Expression;

public class ElseStatement extends Statement {
    private Expression exp;
    private StatementList thenPart;
    private StatementList elsePart;

    public ElseStatement(Expression exp, StatementList thenPart, StatementList elsePart ) {
        this.exp = exp;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public StatementList getThenPart() {
        return thenPart;
    }

    public void setThenPart(StatementList thenPart) {
        this.thenPart = thenPart;
    }

    public StatementList getElsePart() {
        return elsePart;
    }

    public void setElsePart(StatementList elsePart) {
        this.elsePart = elsePart;
    }

    public Object visitElseStatement(ElseStatement elseStatement, Object arg ) {
        return elseStatement.visitElseStatement(this,arg);
    }

}