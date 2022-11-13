package dk.seahawk.parser.ast.terminal;

public class IntegerLiteral extends Terminal {

    public IntegerLiteral( String spelling ) {
        setSpelling(spelling);
    }

    public Object visit(IntegerLiteral integerLiteral, Object arg ) {
        return integerLiteral.visit(this,arg);
    }

}
