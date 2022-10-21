package dk.seahawk.parser.ast.expression;

import dk.seahawk.parser.ast.terminal.Identifier;

public class CallExpression extends Expression {
    public Identifier name;
    public ExpressionList args;

    public CallExpression( Identifier name, ExpressionList args ) {
        this.name = name;
        this.args = args;
    }
}
