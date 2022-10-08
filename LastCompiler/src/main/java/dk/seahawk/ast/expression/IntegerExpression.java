package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class IntegerExpression extends Expression {
    public Identifier name;

    public IntegerExpression( Identifier name ) {
        this.name = name;
    }
}
