package dk.seahawk.ast.terminal;

import dk.seahawk.ast.AST;
import dk.seahawk.ast.declaration.DeclarationList;
import dk.seahawk.ast.statement.StatementList;

public class Block extends AST {
    private DeclarationList decs;
    private StatementList stats;

    public DeclarationList getDecs() {
        return decs;
    }

    public void setDecs(DeclarationList decs) {
        this.decs = decs;
    }

    public StatementList getStats() {
        return stats;
    }

    public void setStats(StatementList stats) {
        this.stats = stats;
    }

    public Block(DeclarationList decs, StatementList stats ) {
        this.decs = decs;
        this.stats = stats;
    }
}
