package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class BoolExpression extends Expression {
    public Identifier name;

    public BoolExpression( Identifier name ) {
        this.name = name;
    }
}
