package dk.seahawk.checker;

import dk.seahawk.parser.ast.*;
import dk.seahawk.parser.ast.declaration.*;
import dk.seahawk.parser.ast.expression.*;
import dk.seahawk.parser.ast.statement.*;
import dk.seahawk.parser.ast.terminal.*;

public interface IVisitor {

    Object visitProgram( Program p, Object arg );

    Object visitDeclarationList(DeclarationList declarationList, Object arg );
    Object visitFunctionDeclaration(FunctionDeclaration functionDeclaration, Object arg );
    Object visitOneDeclaration(OneDeclaration oneDeclaration, Object arg );
    Object visitVariableDeclaration(VariableDeclaration variableDeclaration, Object arg );


    Object visitArrayExpression(ArrayExpression arrayExpression, Object arg );
    Object visitBinaryExpression(BinaryExpression binaryExpression, Object arg );
    Object visitBoolExpression(BoolExpression boolExpression, Object arg );
    Object visitCallExpression(CallExpression callExpression, Object arg );
    Object visitCharExpression(CharExpression charExpression, Object arg );
    Object visitVariableDeclaration(ExpressionList expressionList, Object arg );
    Object visitIntegerExpression(IntegerExpression integerExpression, Object arg );
    Object visitUnaryExpression(UnaryExpression unaryExpression, Object arg );


    Object visitElseStatement(ElseStatement elseStatement, Object arg );
    Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg );
    Object visitIfStatement(IfStatement ifStatement, Object arg );
    Object visitPrintStatement(PrintStatement printStatement, Object arg );
    Object visitUnaryStatementList(UnaryExpression unaryExpression, Object arg );


    Object visitBlock(Block block, Object arg );
    Object visitBoolType(BoolType boolType, Object arg );
    Object visitCharLiteral(CharLiteral charLiteral, Object arg );
    Object visitIdentifier(Identifier identifier, Object arg );
    Object visitIntegerLiteral(IntegerLiteral integerLiteral, Object arg );
    Object visitOperator(Operator operator, Object arg );

}
