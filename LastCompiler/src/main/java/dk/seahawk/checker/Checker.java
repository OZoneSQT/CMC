package dk.seahawk.checker;

import dk.seahawk.parser.IParser;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.parser.ast.declaration.*;
import dk.seahawk.parser.ast.expression.*;
import dk.seahawk.parser.ast.statement.*;
import dk.seahawk.parser.ast.terminal.*;
import dk.seahawk.utils.IErrorHandler;

import java.util.Vector;

public class Checker implements IChecker, IVisitor {

    private IParser parser;
    private IErrorHandler errorHandler;
    private IdentificationTable idTable = new IdentificationTable();

    public Checker(IErrorHandler errorHandler) {
        this.parser = parser;
        this.errorHandler = errorHandler;
    }

    @Override
    public void check( Program program )
    {
        program.visitProgram( this, null );
    }

    @Override
    public Object visitProgram(Program program, Object arg) {
        idTable.openScope();
        program.visitProgram( this, null );
        idTable.closeScope();

        return null;
    }

    @Override
    public Object visitDeclarationList(DeclarationList declarationList, Object arg) {
        for( Declaration declaration: declarationList.declarations )
            declaration.visit( this, null );

        return null;
    }

    @Override
    public Object visitFunctionDeclaration(FunctionDeclaration functionDeclaration, Object arg) {
        String id = (String) functionDeclaration.getName().visitIdentifier( this, null );

        idTable.enter( id, functionDeclaration );
        idTable.openScope();

        functionDeclaration.getParams().visitDeclarationList( this, null );
        functionDeclaration.getBlock().visitBlock(this, null );
        functionDeclaration.getRetExp().visitDeclarationList( this, null );

        idTable.closeScope();

        return null;
    }

    @Override
    public Object visitOneDeclaration(OneDeclaration oneDeclaration, Object arg) {
        return null;
    }

    @Override
    public Object visitVariableDeclaration(VariableDeclaration variableDeclaration, Object arg) {
        String id = (String) v.id.visit( this, null );

        idTable.enter( id, v );

        return null;
    }

    @Override
    public Object visitArrayExpression(ArrayExpression arrayExpression, Object arg) {
        i.literal.visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression binaryExpression, Object arg) {
        Type t1 = (Type) binaryExpression.getOperand1().visitBinaryExpression( this, null );
        Type t2 = (Type) binaryExpression.getOperand2().visitBinaryExpression( this, null );
        String operator = (String) binaryExpression.getOperator().visitBinaryExpression( this, null );

        if( operator.equals( ":=" ) && t1.isRvalueOnly() )
            System.out.println( "Left-hand side of := must be a variable" );

        return new Type( true );
    }

    @Override
    public Object visitBoolExpression(BoolExpression boolExpression, Object arg) {
        i.literal.visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitCallExpression(CallExpression callExpression, Object arg) {
        String id = (String) c.name.visit( this, null );
        Vector<Type> t = (Vector<Type>)( c.args.visit( this, null ) );

        Declaration d = idTable.retrieve( id );
        if( d == null )
            System.out.println( id + " is not declared" );
        else if( !( d instanceof FunctionDeclaration ) )
            System.out.println( id + " is not a function" );
        else {
            FunctionDeclaration f = (FunctionDeclaration) d;
            c.decl = f;

            if( f.params.dec.size() != t.size() )
                System.out.println( "Incorrect number of arguments in call to " + id );
        }

        return new Type( true );
    }

    @Override
    public Object visitCharExpression(CharExpression charExpression, Object arg) {
        i.literal.visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitVariableDeclaration(ExpressionList expressionList, Object arg) {
        String id = (String) v.name.visit( this, null );

        Declaration d = idTable.retrieve( id );
        if( d == null )
            System.out.println( id + " is not declared" );
        else if( !( d instanceof VariableDeclaration ) )
            System.out.println( id + " is not a variable" );
        else
            v.decl = (VariableDeclaration) d;

        return new Type( false );
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression integerExpression, Object arg) {
        i.literal.visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression unaryExpression, Object arg) {
        unaryExpression.getOperand().visitUnaryExpression( this, null );
        String operator = (String) unaryExpression.getOperator().visitUnaryExpression( this, null );

        if( !operator.equals( "+" ) && !operator.equals( "-" ) )
            System.out.println( "Only + or - is allowed as unary operator" );

        return new Type( true );
    }

    @Override
    public Object visitElseStatement(ElseStatement elseStatement, Object arg) {
        w.exp.visit( this, null );
        w.stats.visit( this, null );

        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        w.exp.visit( this, null );
        w.stats.visit( this, null );

        return null;
    }

    @Override
    public Object visitIfStatement(IfStatement ifStatement, Object arg) {
        i.exp.visit( this, null );
        i.thenPart.visit( this, null );
        i.elsePart.visit( this, null );

        return null;
    }

    @Override
    public Object visitPrintStatement(PrintStatement printStatement, Object arg) {
        return null;
    }

    @Override
    public Object visitUnaryStatementList(UnaryExpression unaryExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        block.getDecs().visitDeclarationList(this, null);
        block.getStats().visitUnaryStatementList(this,null);

        return null;
    }

    @Override
    public Object visitBoolType(BoolType boolType, Object arg) {
        return null;
    }

    @Override
    public Object visitCharLiteral(CharLiteral charLiteral, Object arg) {
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier identifier, Object arg) {
        return identifier.getSpelling();
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral integerLiteral, Object arg) {
        return null;
    }

    @Override
    public Object visitOperator(Operator operator, Object arg) {
        return operator.getSpelling();
    }

}
