package dk.seahawk.parser.ast.declaration;

import dk.seahawk.parser.ast.terminal.Identifier;

public class VariableDeclaration extends Declaration {
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

    public Object visit(VariableDeclaration variableDeclaration, Object arg ) {
        return variableDeclaration.visit(this,arg);
    }

}
