package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.terminal.IntegerLiteral;

public class IntegerExpression extends Expression {
    private IntegerLiteral name;
    private VariableDeclaration declaration;

    public IntegerExpression(IntegerLiteral name ) {
        this.name = name;
    }

    public IntegerLiteral getName() {
        return name;
    }

    public void setName(IntegerLiteral name) {
        this.name = name;
    }

    public void setDeclaration(VariableDeclaration declaration) {
        this.declaration = declaration;
    }

    public VariableDeclaration getDeclaration() {
        return declaration;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitIntegerExpression(this, arg);
    }

}
