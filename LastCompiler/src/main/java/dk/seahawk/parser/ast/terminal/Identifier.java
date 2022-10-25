package dk.seahawk.parser.ast.terminal;

public class Identifier extends Terminal {
    public Identifier( String spelling )
    {
        this.setSpelling(spelling);
    }

    public Object visitIdentifier(Identifier identifier, Object arg ) {
        return identifier.visitIdentifier(this,arg);
    }

}
