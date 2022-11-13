package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;

public class Operator extends Terminal {
    public Operator( String spelling )
    {
        this.setSpelling(spelling);
    }

    public Object visit(Operator operator, Object arg ) {
        return operator.visit(this,arg);
    }

    @Override
    public Object visit(IVisitor v, Object arg) {
        return null;
    }
}
