package dk.seahawk.parser.ast.statement;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.expression.Expression;

public class ElseStatement extends Statement {
    private Expression expression;
    private StatementList elsePart;

    public ElseStatement(Expression expression, StatementList elsePart ) {
        this.expression = expression;
        this.elsePart = elsePart;
    }

    public Expression getExp() {
        return expression;
    }

    public void setExp(Expression expression) {
        this.expression = expression;
    }

    public StatementList getElsePart() {
        return elsePart;
    }

    public void setElsePart(StatementList elsePart) {
        this.elsePart = elsePart;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitElseStatement( this, arg);
    }

}
