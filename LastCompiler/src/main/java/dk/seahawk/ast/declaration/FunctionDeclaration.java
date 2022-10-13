package dk.seahawk.ast.declaration;

import dk.seahawk.ast.expression.Expression;
import dk.seahawk.ast.terminal.Block;
import dk.seahawk.ast.terminal.Identifier;

public class FunctionDeclaration {
    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public DeclarationList getParams() {
        return params;
    }

    public void setParams(DeclarationList params) {
        this.params = params;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Expression getRetExp() {
        return retExp;
    }

    public void setRetExp(Expression retExp) {
        this.retExp = retExp;
    }

    private Identifier name;
    private DeclarationList params;
    private Block block;
    private Expression retExp;

    public FunctionDeclaration( Identifier name, DeclarationList params, Block block, Expression retExp ) {
        this.name = name;
        this.params = params;
        this.block = block;
        this.retExp = retExp;
    }
}
