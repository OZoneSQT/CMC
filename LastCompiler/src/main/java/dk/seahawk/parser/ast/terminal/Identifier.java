package dk.seahawk.parser.ast.terminal;

public class Identifier extends Terminal {
    public Identifier( String spelling )
    {
        this.setSpelling(spelling);
    }

    public Object visit(Identifier identifier, Object arg ) {
        return identifier.visit(this,arg);
    }

}
