package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class ExpressionList extends AST {
    public Vector<Expression> expression = new Vector<>();

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitExpressionList(this, arg);
    }

}
