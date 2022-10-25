package dk.seahawk.parser.ast.expression;

import dk.seahawk.parser.ast.terminal.CharLiteral;

public class CharExpression extends Expression {
    private CharLiteral name;

    public CharExpression(CharLiteral name ) {
        this.name = name;
    }

    public CharLiteral getName() {
        return name;
    }

    public void setName(CharLiteral name) {
        this.name = name;
    }

    public Object visitCharExpression(CharExpression charExpression, Object arg ) {
        return charExpression.visitCharExpression(this,arg);
    }

}
