package dk.seahawk.ast;

import dk.seahawk.ast.declaration.Declaration;
import dk.seahawk.ast.declaration.DeclarationList;
import dk.seahawk.ast.declaration.FunctionDeclaration;
import dk.seahawk.ast.declaration.VariableDeclaration;
import dk.seahawk.ast.expression.*;
import dk.seahawk.ast.statement.*;
import dk.seahawk.ast.terminal.Block;
import dk.seahawk.ast.terminal.Identifier;
import dk.seahawk.ast.terminal.IntegerLiteral;
import dk.seahawk.ast.terminal.Operator;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ASTViewer extends JFrame {
    private static final Font NODE_FONT = new Font( "Verdana", Font.PLAIN, 24 );

    public ASTViewer( AST ast ) {
        super( "Abstract Syntax Tree" );

        DefaultMutableTreeNode root = createTree( ast );
        JTree tree = new JTree( root );
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setFont( NODE_FONT );
        tree.setCellRenderer( renderer );

        add( new JScrollPane( tree ) );

        setSize( 1024, 768 );
        setVisible( true );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
    }

    private DefaultMutableTreeNode createTree( AST ast ) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode( "*** WHAT??? ***" );

        if( ast == null ) {
            node.setUserObject("*** NULL ***");
        } else if( ast instanceof Block) {
            node.setUserObject( "Block" );
            node.add( createTree( ((Block)ast).decs ) );
            node.add( createTree( ((Block)ast).stats ) );
        } else if( ast instanceof Declaration) {
            node.setUserObject( "Declarations" );

            for( Declaration d: ((DeclarationList)ast).dec )
                node.add( createTree( d ) );
        } else if( ast instanceof VariableDeclaration) {
            node.setUserObject( "VariableDeclaration" );
            node.add( createTree( ((VariableDeclaration)ast).id ) );
        } else if( ast instanceof FunctionDeclaration) {
            node.setUserObject( "FunctionDeclaration" );
            node.add( createTree( ((FunctionDeclaration)ast).name ) );
            node.add( createTree( ((FunctionDeclaration)ast).params ) );
            node.add( createTree( ((FunctionDeclaration)ast).block ) );
            node.add( createTree( ((FunctionDeclaration)ast).retExp ) );
        } else if( ast instanceof StatementList ) {
            node.setUserObject( "Statements" );

            for( Statement s: ((StatementList)ast).stat )
                node.add( createTree( s ) );
        } else if( ast instanceof ExpressionStatement) {
            node.setUserObject( "ExpressionStatement" );
            node.add( createTree( ((ExpressionStatement)ast).exp ) );
        } else if( ast instanceof IfStatement) {
            node.setUserObject( "IfStatement" );
            node.add( createTree( ((IfStatement)ast).exp ) );
            node.add( createTree( ((IfStatement)ast).thenPart ) );
            node.add( createTree( ((IfStatement)ast).elsePart ) );
        } else if( ast instanceof PrintStatement ) {
            node.setUserObject( "PrintStatement" );
            node.add( createTree( ((PrintStatement)ast).exp ) );
        } else if( ast instanceof BinaryExpression) {
            node.setUserObject( "BinaryExpression" );
            node.add( createTree( ((BinaryExpression)ast).operator ) );
            node.add( createTree( ((BinaryExpression)ast).operand1 ) );
            node.add( createTree( ((BinaryExpression)ast).operand2 ) );
        } else if( ast instanceof ArrayExpression) {
            node.setUserObject( "ArrayExpression" );
            node.add( createTree( ((ArrayExpression)ast).name ) );
            node.add( createTree( ((ArrayExpression)ast).args ) );
        } else if( ast instanceof CharExpression) {
            node.setUserObject( "CharExpression" );
            node.add( createTree( ((CharExpression)ast).name ) );
            node.add( createTree( ((CharExpression)ast).args ) );
        } else if( ast instanceof IntegerExpression) {
            node.setUserObject( "IntegerExpression" );
            node.add( createTree( ((IntegerExpression)ast).name ) );
            node.add( createTree( ((IntegerExpression)ast).args ) );
        } else if( ast instanceof BoolExpression ) {
            node.setUserObject( "BoolExpression" );
            node.add( createTree( ((BoolExpression)ast).literal ) );
        } else if( ast instanceof UnaryExpression ) {
            node.setUserObject( "UnaryExpression" );
            node.add( createTree( ((UnaryExpression)ast).operator ) );
            node.add( createTree( ((UnaryExpression)ast).operand ) );
        } else if( ast instanceof ExpressionList ) {
            node.setUserObject( "ExpList" );

            for( Expression e: ((ExpressionList)ast).exp )
                node.add( createTree( e ) );
        } else if( ast instanceof Identifier) {
            node.setUserObject( "Identifier " + ((Identifier)ast).spelling );
        } else if( ast instanceof IntegerLiteral) {
            node.setUserObject( "IntegerLiteral " + ((IntegerLiteral)ast).spelling );
        } else if( ast instanceof Operator) {
            node.setUserObject( "Operator " + ((Operator)ast).spelling );
        }

        return node;
    }
}
