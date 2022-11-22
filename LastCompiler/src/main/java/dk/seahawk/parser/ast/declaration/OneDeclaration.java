package dk.seahawk.parser.ast.declaration;

import dk.seahawk.checker.IVisitor;

public class OneDeclaration {

    public Object visitOneDeclaration(IVisitor visitor, Object arg ) {
        return visitor.visitOneDeclaration(this, arg);
    }

}

