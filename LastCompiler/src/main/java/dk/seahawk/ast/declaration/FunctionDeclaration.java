package dk.seahawk.ast.declaration;

import dk.seahawk.ast.expression.Expression;
import dk.seahawk.ast.terminal.Block;
import dk.seahawk.ast.terminal.Identifier;

public class FunctionDeclaration {
    public Identifier name;
    public DeclarationList params;
    public Block block;
    public Expression retExp;

    public FunctionDeclaration( Identifier name, DeclarationList params, Block block, Expression retExp ) {
        this.name = name;
        this.params = params;
        this.block = block;
        this.retExp = retExp;
    }
}
