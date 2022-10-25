package dk.seahawk.parser.ast.statement;

import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class StatementList  extends AST {
    public Vector<Statement> statements = new Vector<>();

    public Object visitUnaryStatementList(StatementList statementList, Object arg ) {
        return statementList.visitUnaryStatementList(this,arg);
    }

}
