package dk.seahawk.parser.ast.terminal;

public class Operator extends Terminal {
    public Operator( String spelling )
    {
        this.setSpelling(spelling);
    }

    public Object visitOperator(Operator operator, Object arg ) {
        return operator.visitOperator(this,arg);
    }

}
