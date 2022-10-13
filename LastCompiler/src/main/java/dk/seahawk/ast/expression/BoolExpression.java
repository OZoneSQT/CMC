package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class BoolExpression extends Expression {
    private Identifier name;

    public BoolExpression(Identifier name ) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

}
