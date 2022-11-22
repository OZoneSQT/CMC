package dk.seahawk.parser.ast.terminal;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.AST;
import dk.seahawk.parser.ast.declaration.DeclarationList;
import dk.seahawk.parser.ast.statement.StatementList;

public class Block extends AST {

    private DeclarationList declarationList;
    private StatementList statementList;

    public Block(DeclarationList declarationList, StatementList statementList ) {
        this.declarationList = declarationList;
        this.statementList = statementList;
    }

    public DeclarationList getDecs() {
        return declarationList;
    }

    public StatementList getStats() {
        return statementList;
    }

    public void setDeclarationList(DeclarationList declarationList) {
        this.declarationList = declarationList;
    }

    public void setStats(StatementList statementList) {
        this.statementList = statementList;
    }

    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitBlock( this, arg);
    }

}
