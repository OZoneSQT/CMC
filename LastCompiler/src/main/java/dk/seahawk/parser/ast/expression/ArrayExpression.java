package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.terminal.Identifier;

public class ArrayExpression extends Expression {
    private Identifier name;
    private VariableDeclaration declaration;

    public ArrayExpression(Identifier name ) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public void setDeclaration(VariableDeclaration declaration) {
        this.declaration = declaration;
    }

    public VariableDeclaration getDeclaration() {
        return declaration;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitArrayExpression(this,arg);
    }

}
