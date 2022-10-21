package dk.seahawk.parser.ast.declaration;

import dk.seahawk.parser.ast.AST;

import java.util.Vector;

public class DeclarationList extends AST {
    public Vector<Declaration> declarations = new Vector<Declaration>();
}
