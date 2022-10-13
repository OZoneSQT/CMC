package dk.seahawk.ast.declaration;

import dk.seahawk.ast.terminal.Identifier;

public class VariableDeclaration {
    private Identifier identifier;

    public VariableDeclaration( Identifier identifier ) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}
