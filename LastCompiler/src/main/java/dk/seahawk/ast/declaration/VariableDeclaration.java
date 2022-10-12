package dk.seahawk.ast.declaration;

import dk.seahawk.ast.terminal.Identifier;

public class VariableDeclaration {
    public Identifier identifier;

    public VariableDeclaration( Identifier identifier ) {
        this.identifier = identifier;
    }
}
