package dk.seahawk.parser.ast.declaration;

import dk.seahawk.parser.ast.expression.Expression;
import dk.seahawk.parser.ast.terminal.Block;
import dk.seahawk.parser.ast.terminal.Identifier;

public class FunctionDeclaration extends Declaration {

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

    public Identifier getName() {
        return name;
    }

    public DeclarationList getParams() {
        return params;
    }

    public Block getBlock() {
        return block;
    }

    public Expression getRetExp() {
        return retExp;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public void setParams(DeclarationList params) {
        this.params = params;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setRetExp(Expression retExp) {
        this.retExp = retExp;
    }
}
