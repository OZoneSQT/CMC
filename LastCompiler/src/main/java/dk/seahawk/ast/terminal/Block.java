package dk.seahawk.ast.terminal;

import dk.seahawk.ast.AST;
import dk.seahawk.ast.declaration.DeclarationList;
import dk.seahawk.ast.statement.StatementList;

public class Block extends AST {
    public DeclarationList decs;
    public StatementList stats;

    public Block( DeclarationList decs, StatementList stats ) {
        this.decs = decs;
        this.stats = stats;
    }
}
