package dk.seahawk.parser.ast.statement;

import dk.seahawk.checker.Checker;
import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class StatementList  extends AST {
    public Vector<Statement> statements = new Vector<>();

    public Object visit(Checker statementList, Object arg ) {
        return statementList.visit(this,arg);
    }

}
