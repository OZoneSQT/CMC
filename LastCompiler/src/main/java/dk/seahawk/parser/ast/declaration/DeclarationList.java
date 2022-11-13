package dk.seahawk.parser.ast.declaration;

import dk.seahawk.checker.Checker;
import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class DeclarationList extends AST {
    public Vector<Declaration> declarations = new Vector<>();

    public Object visit(Checker declarationList, Object arg ) {
        return declarationList.visit(this, arg);
    }

}
