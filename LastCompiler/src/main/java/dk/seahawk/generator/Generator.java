package dk.seahawk.generator;

import dk.seahawk.checker.IVisitor;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.parser.ast.declaration.DeclarationList;
import dk.seahawk.parser.ast.declaration.FunctionDeclaration;
import dk.seahawk.parser.ast.declaration.OneDeclaration;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.expression.*;
import dk.seahawk.parser.ast.statement.*;
import dk.seahawk.parser.ast.terminal.*;
import dk.seahawk.utils.IErrorHandler;

public class Generator implements IGenerator, IVisitor {

    private IErrorHandler errorHandler;
    private Encoder encoder;

    public Generator(IErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        this.encoder = new Encoder(errorHandler);
    }

    @Override
    public void generate(Program program) {
        visitProgram(program, null);
    }

    @Override
    public void saveTargetProgram(String targetName) {
        encoder.saveTargetProgram(targetName);
    }


    @Override
    public Object visitProgram(Program program, Object arg) {
        encoder.setCurrentLevel(0);

        vi

        return null;
    }

    @Override
    public Object visitDeclarationList(DeclarationList declarationList, Object arg) {
        return null;
    }

    @Override
    public Object visitFunctionDeclaration(FunctionDeclaration functionDeclaration, Object arg) {
        return null;
    }

    @Override
    public Object visitOneDeclaration(OneDeclaration oneDeclaration, Object arg) {
        return null;
    }

    @Override
    public Object visitVariableDeclaration(VariableDeclaration variableDeclaration, Object arg) {
        return null;
    }

    @Override
    public Object visitArrayExpression(ArrayExpression arrayExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression binaryExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitBoolExpression(BoolExpression boolExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitCallExpression(CallExpression callExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitCharExpression(CharExpression charExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression integerExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression unaryExpression, Object arg) {
        return null;
    }

    @Override
    public Object visitExpressionList(ExpressionList expressionList, Object arg) {
        return null;
    }

    @Override
    public Object visitElseStatement(ElseStatement elseStatement, Object arg) {
        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        return null;
    }

    @Override
    public Object visitIfStatement(IfStatement ifStatement, Object arg) {
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

    @Override
    public Object visitBlock(Block block, Object arg) {
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
        return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral integerLiteral, Object arg) {
        return null;
    }

    @Override
    public Object visitOperator(Operator operator, Object arg) {
        return null;
    }

}
