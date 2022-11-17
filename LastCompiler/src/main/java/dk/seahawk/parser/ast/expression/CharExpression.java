package dk.seahawk.parser.ast.expression;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.terminal.CharLiteral;

public class CharExpression extends Expression {
    private CharLiteral name;
    private VariableDeclaration declaration;

    public CharExpression(CharLiteral name ) {
        this.name = name;
    }

    public CharLiteral getName() {
        return name;
    }

    public void setName(CharLiteral name) {
        this.name = name;
    }

    public void setDeclaration(VariableDeclaration declaration) {
        this.declaration = declaration;
    }

    public VariableDeclaration getDeclaration() {
        return declaration;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitCharExpression(this,arg);
    }

}
