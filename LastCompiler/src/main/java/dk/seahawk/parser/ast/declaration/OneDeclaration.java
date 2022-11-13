package dk.seahawk.parser.ast.declaration;

public class OneDeclaration {

    public Object visiOneDeclaration(OneDeclaration oneDeclaration, Object arg ) {
        return oneDeclaration.visit(this,arg);
    }
}

