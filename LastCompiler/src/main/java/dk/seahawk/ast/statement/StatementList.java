package dk.seahawk.ast.statement;

import dk.seahawk.ast.AST;

import java.util.Vector;

public class StatementList  extends AST {
    public Vector<Statement> statements = new Vector<Statement>();
}
