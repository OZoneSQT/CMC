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

    private IErrorHandler errorHandler;
    private IdentificationTable idTable = new IdentificationTable();

    public Checker(IErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void check( Program program ) {
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
        String id = (String) functionDeclaration.getName().visit( this, null );

        idTable.enter( id, functionDeclaration );
        idTable.openScope();

        functionDeclaration.getParams().visit( this, null );
        functionDeclaration.getBlock().visit(this, null );
        functionDeclaration.getRetExp().visit( this, null );

        idTable.closeScope();

        return null;
    }

    @Override
    public Object visitOneDeclaration(OneDeclaration oneDeclaration, Object arg) {
        return null;
    }

    //TODO
    @Override
    public Object visitVariableDeclaration(VariableDeclaration variableDeclaration, Object arg) {
        String id = (String) variableDeclaration.getIdentifier().visit( this, null );

        idTable.enter( id, variableDeclaration );

        return null;
    }

    @Override
    public Object visitArrayExpression(ArrayExpression arrayExpression, Object arg) {
        arrayExpression.getName().visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression binaryExpression, Object arg) {
        Type t1 = (Type) binaryExpression.getOperand1().visit( this, null );
        Type t2 = (Type) binaryExpression.getOperand2().visit( this, null );
        String operator = (String) binaryExpression.getOperator().visit( this, null );

        if( operator.equals( ":=" ) && t1.isRvalueOnly() )
            System.out.println( "Left-hand side of := must be a variable" );

        return new Type( true );
    }

    @Override
    public Object visitBoolExpression(BoolExpression boolExpression, Object arg) {
        boolExpression.getName().visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitCallExpression(CallExpression callExpression, Object arg) {
        String id = (String) callExpression.getName().visit( this, null );
        Vector<Type> t = (Vector<Type>)( callExpression.getExpressionList().visit( this, null ) );

        Declaration declaration = idTable.retrieve( id );
        if( declaration == null )
            System.out.println( id + " is not declared" );
        else if( !( declaration instanceof FunctionDeclaration ) )
            System.out.println( id + " is not a function" );
        else {
            FunctionDeclaration functionDeclaration = (FunctionDeclaration) declaration;
            //TODO ?
            callExpression.setFunctionDeclaration(functionDeclaration);

            if( functionDeclaration.getParams().declarations.size() != t.size() )
                System.out.println( "Incorrect number of arguments in call to " + id );
        }

        return new Type( true );
    }

    @Override
    public Object visitCharExpression(CharExpression charExpression, Object arg) {
        charExpression.getName().visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression integerExpression, Object arg) {
        integerExpression.getName().visit( this, true );

        return new Type( true );
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression unaryExpression, Object arg) {
        unaryExpression.getOperand().visit( this, null );
        String operator = (String) unaryExpression.getOperator().visit( this, null );

        if( !operator.equals( "+" ) && !operator.equals( "-" ) )
            System.out.println( "Only + or - is allowed as unary operator" );

        return new Type( true );
    }

    @Override
    public Object visitExpressionList(ExpressionList expressionList, Object arg) {
        return null;
    }

    @Override
    public Object visitElseStatement(ElseStatement elseStatement, Object arg) {
        elseStatement.getExp().visit( this, null );
        elseStatement.getElsePart().visit( this, null );

        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        expressionStatement.getExpression().visit( this, null );

        return null;
    }

    @Override
    public Object visitIfStatement(IfStatement ifStatement, Object arg) {
        ifStatement.getExp().visit(this, null);
        ifStatement.getElseExp().visit( this, null );

        return null;
    }

    @Override
    public Object visitPrintStatement(PrintStatement printStatement, Object arg) {
        return null;
    }

    @Override
    public Object visitStatementList(StatementList statementList, Object arg) {
        return null;
    }


    public Object visitUnaryStatementList(UnaryExpression unaryExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        block.getDecs().visit(this, null);
        block.getStats().visit(this,null);

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
