package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.declaration.FunctionDeclaration;
import dk.seahawk.parser.ast.terminal.Identifier;

public class CallExpression extends Expression {
    private Identifier name;
    private ExpressionList expressionList;
    private FunctionDeclaration functionDeclaration;


    public CallExpression(Identifier name, ExpressionList expressionList) {
        this.name = name;
        this.expressionList = expressionList;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public ExpressionList getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(ExpressionList expressionList) {
        this.expressionList = expressionList;
    }

    public FunctionDeclaration getFunctionDeclaration() {
        return functionDeclaration;
    }

    public void setFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        this.functionDeclaration = functionDeclaration;
    }

    @Override
    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitCallExpression(this, null);
    }

}
