package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class CharExpression extends Expression {
    private Identifier name;

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public CharExpression(Identifier name ) {
        this.name = name;
    }
}
