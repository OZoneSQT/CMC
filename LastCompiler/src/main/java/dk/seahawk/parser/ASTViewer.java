package dk.seahawk.parser;

import dk.seahawk.parser.ast.AST;
import dk.seahawk.parser.ast.declaration.Declaration;
import dk.seahawk.parser.ast.declaration.DeclarationList;
import dk.seahawk.parser.ast.declaration.FunctionDeclaration;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.statement.*;
import dk.seahawk.parser.ast.terminal.Block;
import dk.seahawk.parser.ast.terminal.Identifier;
import dk.seahawk.parser.ast.terminal.IntegerLiteral;
import dk.seahawk.parser.ast.terminal.Operator;
import dk.seahawk.parser.ast.expression.*;

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

    //TODO Errors: ex. for( Declaration d: ((DeclarationList)ast).dec ) error at .dec => public variable
    // Solution, Start by adding Getters and Setters for private variables
    private DefaultMutableTreeNode createTree( AST ast ) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode( "*** WHAT??? ***" );

        if( ast == null ) {
            node.setUserObject("*** NULL ***");
        } else if( ast instanceof Block) {
            node.setUserObject( "Block" );
            node.add( createTree( ((Block)ast).getDecs()) );
            node.add( createTree( ((Block)ast).getStats() ) );
        } else if( ast instanceof Declaration) {
            node.setUserObject( "Declarations" );
            for( Declaration d: ((DeclarationList)ast).declarations )
                node.add( createTree( d ) );
        } else if( ast instanceof VariableDeclaration) {
            node.setUserObject( "VariableDeclaration" );
            node.add( createTree( ((VariableDeclaration)ast).getIdentifier() ) );
        } else if( ast instanceof FunctionDeclaration) {
            node.setUserObject( "FunctionDeclaration" );
            node.add( createTree( ((FunctionDeclaration)ast).getName() ) );
            node.add( createTree( ((FunctionDeclaration)ast).getParams() ) );
            node.add( createTree( ((FunctionDeclaration)ast).getBlock() ) );
            node.add( createTree( ((FunctionDeclaration)ast).getRetExp() ) );
        } else if( ast instanceof StatementList) {
            node.setUserObject( "Statements" );

            for( Statement s: ((StatementList)ast).statements )
                node.add( createTree( s ) );
        } else if( ast instanceof ExpressionStatement) {
            node.setUserObject( "ExpressionStatement" );
            node.add( createTree( ((ExpressionStatement)ast).getExp() ) );
        } else if( ast instanceof IfStatement) {
            node.setUserObject("IfStatement");
            node.add(createTree(((IfStatement) ast).getExp()));
            node.add(createTree(((IfStatement) ast).getElseExp()));
        } else if( ast instanceof ElseStatement) {
                node.setUserObject( "ElseStatement" );
                node.add( createTree( ((ElseStatement)ast).getExp() ) );
                node.add( createTree( ((ElseStatement)ast).getThenPart() ) );
                node.add( createTree( ((ElseStatement)ast).getElsePart() ) );
        } else if( ast instanceof PrintStatement) {
            node.setUserObject( "PrintStatement" );
            node.add( createTree( ((PrintStatement)ast).getExp() ) );
        } else if( ast instanceof BinaryExpression) {
            node.setUserObject( "BinaryExpression" );
            node.add( createTree( ((BinaryExpression)ast).getOperator() ) );
            node.add( createTree( ((BinaryExpression)ast).getOperand1() ) );
            node.add( createTree( ((BinaryExpression)ast).getOperand2() ) );
        } else if( ast instanceof ArrayExpression) {
            node.setUserObject( "ArrayExpression" );
            node.add( createTree( ((ArrayExpression)ast).getName() ) );
            //node.add( createTree( ((ArrayExpression)ast).args ) );
        } else if( ast instanceof CharExpression) {
            node.setUserObject( "CharExpression" );
            node.add( createTree( ((CharExpression)ast).getName() ) );
            //node.add( createTree( ((CharExpression)ast).args ) );
        } else if( ast instanceof IntegerExpression) {
            node.setUserObject( "IntegerExpression" );
            node.add( createTree( ((IntegerExpression)ast).getName() ) );
            //node.add( createTree( ((IntegerExpression)ast).args ) );
        } else if( ast instanceof BoolExpression) {
            node.setUserObject( "BoolExpression" );
            node.add( createTree( ((BoolExpression)ast).getName() ) );
            //node.add( createTree( ((BoolExpression)ast).args ) );
        } else if( ast instanceof UnaryExpression ) {
            node.setUserObject( "UnaryExpression" );
            node.add( createTree( ((UnaryExpression)ast).getOperator() ) );
            node.add( createTree( ((UnaryExpression)ast).getOperand() ) );
        } else if( ast instanceof ExpressionList ) {
            node.setUserObject( "ExpList" );

            for( Expression e: ((ExpressionList)ast).expression)
                node.add( createTree( e ) );
        } else if( ast instanceof Identifier) {
            node.setUserObject( "Identifier " + ((Identifier)ast).getSpelling() );
        } else if( ast instanceof IntegerLiteral) {
            node.setUserObject( "IntegerLiteral " + ((IntegerLiteral)ast).getSpelling() );
        } else if( ast instanceof Operator) {
            node.setUserObject( "Operator " + ((Operator)ast).getSpelling() );
        }

        return node;
    }
}
