package dk.seahawk.ast.declaration;

import dk.seahawk.ast.AST;

import java.util.Vector;

public class DeclarationList extends AST {
    public Vector<Declaration> declarations = new Vector<Declaration>();
}
