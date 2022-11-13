package dk.seahawk.parser.ast.terminal;

public class CharLiteral extends Terminal {

    public CharLiteral( String spelling ) {
        setSpelling(spelling);
    }

    public Object visit(CharLiteral charLiteral, Object arg ) {
        return charLiteral.visit(this,arg);
    }

}