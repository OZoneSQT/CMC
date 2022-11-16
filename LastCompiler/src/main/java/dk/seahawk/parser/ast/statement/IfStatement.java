package dk.seahawk.parser.ast.statement;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.expression.Expression;

public class IfStatement extends Statement {
    private Expression exp;
    private StatementList elseExp;

    public IfStatement(Expression ifExp, StatementList elseExp ) {
        this.exp = exp;
        this.elseExp = elseExp;
    }

    public StatementList getElseExp() {
        return elseExp;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public void setElsePart(StatementList elsePart) {
        this.elseExp = elsePart;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitIfStatement( this, arg);
    }

}
