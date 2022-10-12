package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class ArrayExpression extends Expression {
    public Identifier name;

    public ArrayExpression( Identifier name ) {
        this.name = name;
    }
}
