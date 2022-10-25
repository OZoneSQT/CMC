package dk.seahawk.parser.ast.terminal;

import dk.seahawk.parser.ast.AST;
import dk.seahawk.parser.ast.declaration.DeclarationList;
import dk.seahawk.parser.ast.statement.StatementList;

public class Block extends AST {


    private DeclarationList decs;
    private StatementList stats;

    public Block(DeclarationList decs, StatementList stats ) {
        this.decs = decs;
        this.stats = stats;
    }

    public DeclarationList getDecs() {
        return decs;
    }

    public StatementList getStats() {
        return stats;
    }

    public void setDecs(DeclarationList decs) {
        this.decs = decs;
    }

    public void setStats(StatementList stats) {
        this.stats = stats;
    }

    public Object visitBlock(Block block, Object arg ) {
        return block.visitBlock(this,arg);
    }

}
