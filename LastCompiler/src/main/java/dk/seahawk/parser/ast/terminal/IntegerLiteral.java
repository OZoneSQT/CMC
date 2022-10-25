package dk.seahawk.parser.ast.terminal;

public class IntegerLiteral extends Terminal {

    public IntegerLiteral( String spelling ) {
        setSpelling(spelling);
    }

    public Object visitIntegerLiteral(IntegerLiteral integerLiteral, Object arg ) {
        return integerLiteral.visitIntegerLiteral(this,arg);
    }

}
