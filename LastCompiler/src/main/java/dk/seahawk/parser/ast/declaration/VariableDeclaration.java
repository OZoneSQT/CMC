package dk.seahawk.parser.ast.declaration;

import dk.seahawk.checker.Address;
import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.terminal.Identifier;

public class VariableDeclaration extends Declaration {
    private Identifier identifier;
    private VariableDeclaration variableDeclaration;
    public Address address;

    public VariableDeclaration( Identifier identifier ) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Object visit(IVisitor visitor, Object arg) {
        return visitor.visitVariableDeclaration(this, arg);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

}
