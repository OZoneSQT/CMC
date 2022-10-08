package dk.seahawk.ast.expression;

import dk.seahawk.ast.terminal.Identifier;

public class CharExpression extends Expression {
    public Identifier name;

    public CharExpression( Identifier name ) {
        this.name = name;
    }
}
