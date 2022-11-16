package dk.seahawk.parser.ast.statement;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.expression.Expression;

public class PrintStatement extends Statement {
    private Expression exp;

    public PrintStatement(Expression exp ) {
        this.exp = exp;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitPrintStatement( this, arg);
    }

}
