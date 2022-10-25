package dk.seahawk.parser.ast.terminal;

public class CharLiteral extends Terminal {

    public CharLiteral( String spelling ) {
        setSpelling(spelling);
    }

    public Object visitCharCharLiteral(CharLiteral charLiteral, Object arg ) {
        return charLiteral.visitCharCharLiteral(this,arg);
    }

}