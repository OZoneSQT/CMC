package dk.seahawk.parser.ast.expression;

import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class ExpressionList extends AST {
    public Vector<Expression> expression = new Vector<>();

    public Object visit(ExpressionList expressionList, Object arg ) {
        return expressionList.visit(this,arg);
    }

}
