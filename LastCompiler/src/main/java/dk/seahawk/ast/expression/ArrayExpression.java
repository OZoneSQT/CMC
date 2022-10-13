package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class ArrayExpression extends Expression {
    private Identifier name;

    public ArrayExpression(Identifier name ) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

}
