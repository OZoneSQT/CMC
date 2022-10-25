package dk.seahawk.parser.ast.terminal;

import dk.seahawk.parser.ast.expression.CallExpression;

public class BoolType extends Terminal {

    // true or false
    public BoolType( String spelling ) {
        setSpelling(spelling);
    }

    public Object visitBoolType(BoolType boolType, Object arg ) {
        return boolType.visitBoolType(this,arg);
    }

}