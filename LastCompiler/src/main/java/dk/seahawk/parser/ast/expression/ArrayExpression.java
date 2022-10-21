package dk.seahawk.parser.ast.expression;

import dk.seahawk.parser.ast.terminal.Identifier;

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
