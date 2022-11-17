package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.terminal.BoolType;

public class BoolExpression extends Expression {
    private BoolType name;
    private VariableDeclaration declaration;

    public BoolExpression(BoolType name ) {
        this.name = name;
    }

    public BoolType getName() {
        return name;
    }

    public void setName(BoolType name) {
        this.name = name;
    }

    public void setDeclaration(VariableDeclaration declaration) {
        this.declaration = declaration;
    }

    public VariableDeclaration getDeclaration() {
        return declaration;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitBoolExpression(this,arg);
    }

}
