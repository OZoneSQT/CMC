package dk.seahawk.parser.ast.declaration;

public class OneDeclaration {

    public Object visitOneDeclaration(OneDeclaration oneDeclaration, Object arg ) {
        return oneDeclaration.visitOneDeclaration(this,arg);
    }
}

