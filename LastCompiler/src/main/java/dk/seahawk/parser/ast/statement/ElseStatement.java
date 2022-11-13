package dk.seahawk.parser.ast.statement;

import dk.seahawk.parser.ast.expression.Expression;

public class ElseStatement extends Statement {
    private Expression exp;
    private StatementList elsePart;

    public ElseStatement(Expression exp, StatementList elsePart ) {
        this.exp = exp;
        this.elsePart = elsePart;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public StatementList getElsePart() {
        return elsePart;
    }

    public void setElsePart(StatementList elsePart) {
        this.elsePart = elsePart;
    }

    public Object visit(ElseStatement elseStatement, Object arg ) {
        return elseStatement.visit(this,arg);
    }

}
