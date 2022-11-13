package dk.seahawk.parser.ast.expression;

import dk.seahawk.parser.ast.terminal.Identifier;

public class CallExpression extends Expression {
    public Identifier name;
    public ExpressionList args;

    public CallExpression( Identifier name, ExpressionList args ) {
        this.name = name;
        this.args = args;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public ExpressionList getArgs() {
        return args;
    }

    public void setArgs(ExpressionList args) {
        this.args = args;
    }

    public Object visit(CallExpression callExpression, Object arg ) {
        return callExpression.visit(this,arg);
    }

}
