package dk.seahawk.parser.ast.statement;

import dk.seahawk.checker.Checker;
import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class StatementList  extends AST {
    public Vector<Statement> statements = new Vector<>();

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitStatementList(this, arg);
    }

}
