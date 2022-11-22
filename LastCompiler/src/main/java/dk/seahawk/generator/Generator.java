package dk.seahawk.generator;

import dk.seahawk.checker.Address;
import dk.seahawk.checker.IVisitor;
import dk.seahawk.generator.tam.Machine;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.parser.ast.declaration.*;
import dk.seahawk.parser.ast.expression.*;
import dk.seahawk.parser.ast.statement.*;
import dk.seahawk.parser.ast.terminal.*;
import dk.seahawk.utils.IErrorHandler;

public final class Generator implements IGenerator, IVisitor {

    private IErrorHandler errorHandler;
    private Encoder encoder;

    public Generator(IErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        this.encoder = new Encoder(errorHandler);
    }


    /*
     * Util methods
     */

    @Override
    public void generate(Program program) {
        visitProgram(program, null);
    }

    @Override
    public void saveTargetProgram(String targetName) {
        encoder.saveTargetProgram(targetName);
    }


    /*
    * Generator methods
    */

    @Override
    public Object visitProgram(Program program, Object arg) {
        encoder.setCurrentLevel(0);

        program.getBlock().visit(this, new Address() );

        encoder.emit( Machine.HALTop, 0, 0, 0 );

        return null;
    }

    @Override
    public Object visitDeclarationList(DeclarationList declarationList, Object arg) {
        int startDisplacement = ((Address) arg).displacement;

        for( Declaration declaration : declarationList.declarations )
            arg = declaration.visit( this, arg );

        Address adr = (Address) arg;
        int size = adr.displacement - startDisplacement;

        return new Integer( size );
    }

    @Override
    public Object visitFunctionDeclaration(FunctionDeclaration functionDeclaration, Object arg) {
        functionDeclaration.setAddress(new Address( encoder.getCurrentLevel(), encoder.getNextAdr() ));

        encoder.setCurrentLevel(encoder.getCurrentLevel() + 1);

        Address adr = new Address( (Address) arg );

        int size = ((Integer) functionDeclaration.getParams().visit( this, adr ) ).intValue();
        functionDeclaration.getParams().visit( this, new Address( adr, -size ) );

        functionDeclaration.getBlock().visit( this, new Address( adr, Machine.linkDataSize ) );
        functionDeclaration.getRetExp().visit( this, new Boolean( true ) );

        encoder.emit( Machine.RETURNop, 1, 0, size );

        encoder.setCurrentLevel(encoder.getCurrentLevel() - 1);

        return arg;
    }

    @Override
    public Object visitOneDeclaration(OneDeclaration oneDeclaration, Object arg) {
        return null;
    }

    @Override
    public Object visitVariableDeclaration(VariableDeclaration variableDeclaration, Object arg) {
        variableDeclaration.setAddress((Address) arg);

        return new Address( (Address) arg, 1 );
    }

    @Override
    public Object visitArrayExpression(ArrayExpression arrayExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        Integer lit = (Integer) arrayExpression.visit( this, null );

        if( valueNeeded )
            encoder.emit( Machine.LOADLop, 1, 0, lit.intValue() );

        return null;
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression binaryExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        String operator = (String) binaryExpression.getOperator().visit( this, null );

        if( operator.equals( ":=" ) ) {
            Address address = (Address) binaryExpression.getOperand1().visit( this, false );
            binaryExpression.getOperand2().visit( this, new Boolean( true ) );

            int register = encoder.displayRegister( encoder.getCurrentLevel(), address.level );
            encoder.emit( Machine.STOREop, 1, register, address.displacement );

            if( valueNeeded )
                encoder.emit( Machine.LOADop, 1, register, address.displacement );
        } else {
            binaryExpression.getOperand1().visit( this, arg );
            binaryExpression.getOperand2().visit( this, arg );

            if( valueNeeded )
                if( operator.equals( "+" ) )
                    encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.addDisplacement );
                else if( operator.equals( "-" ) )
                    encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.subDisplacement );
                else if( operator.equals( "*" ) )
                    encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.multDisplacement );
                else if( operator.equals( "/" ) )
                    encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.divDisplacement );
                else if( operator.equals( "%" ) )
                    encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.modDisplacement );
        }

        return null;
    }

    @Override
    public Object visitBoolExpression(BoolExpression boolExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        Integer lit = (Integer) boolExpression.visit( this, null );

        if( valueNeeded )
            encoder.emit( Machine.LOADLop, 1, 0, lit.intValue() );

        return null;
    }

    @Override
    public Object visitCallExpression(CallExpression callExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        callExpression.getExpressionList().visit( this, null );

        Address adr = callExpression.getFunctionDeclaration().address;
        int register = encoder.displayRegister( encoder.getCurrentLevel(), adr.level );

        encoder.emit( Machine.CALLop, register, Machine.CB, adr.displacement );

        if( !valueNeeded )
            encoder.emit( Machine.POPop, 0, 0, 1 );

        return null;
    }

    @Override
    public Object visitCharExpression(CharExpression charExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        Integer lit = (Integer) charExpression.visit( this, null );

        if( valueNeeded )
            encoder.emit( Machine.LOADLop, 1, 0, lit.intValue() );

        return null;
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression integerExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        Integer lit = (Integer) integerExpression.visit( this, null );

        if( valueNeeded )
            encoder.emit( Machine.LOADLop, 1, 0, lit.intValue() );

        return null;
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression unaryExpression, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        String operator = (String) unaryExpression.getOperator().visit( this, null );
        unaryExpression.getOperand().visit( this, arg );

        if( valueNeeded && operator.equals( "-") )
            encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.negDisplacement );

        return null;
    }

    @Override
    public Object visitExpressionList(ExpressionList expressionList, Object arg) {
        for( Expression exp: expressionList.expression )
            exp.visit( this, new Boolean( true ) );

        return null;
    }

    @Override
    public Object visitElseStatement(ElseStatement elseStatement, Object arg) {
        elseStatement.getExp().visit( this, new Boolean( true ) );

        int jump1Adr = encoder.getNextAdr();
        encoder.emit( Machine.JUMPIFop, 0, Machine.CBr, 0 );

        encoder.patch( jump1Adr, encoder.getNextAdr() );

        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        expressionStatement.getExpression().visit( this, new Boolean( false ) );

        return null;
    }

    @Override
    public Object visitIfStatement(IfStatement ifStatement, Object arg) {
        ifStatement.getExp().visit( this, new Boolean( true ) );

        int jump1Adr = encoder.getNextAdr();
        encoder.emit( Machine.JUMPIFop, 0, Machine.CBr, 0 );

        encoder.patch( jump1Adr, encoder.getNextAdr() );

        return null;
    }

    @Override
    public Object visitPrintStatement(PrintStatement printStatement, Object arg) {
        printStatement.getExp().visit( this, new Boolean( true ) );

        encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.putintDisplacement );
        encoder.emit( Machine.CALLop, 0, Machine.PBr, Machine.puteolDisplacement );

        return null;
    }

    @Override
    public Object visitStatementList(StatementList statementList, Object arg) {
        for( Statement statement : statementList.statements )
            statement.visit( this, null );

        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        int before = encoder.getNextAdr();
        encoder.emit( Machine.JUMPop, 0, Machine.CB, 0 );

        int size = ((Integer) block.getDecs().visit( this, arg )).intValue();

        encoder.patch( before, encoder.getNextAdr() );

        if( size > 0 )
            encoder.emit( Machine.PUSHop, 0, 0, size );

        block.getStats().visit( this, null );

        return size;
    }

    @Override
    public Object visitBoolType(BoolType boolType, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        BoolType lit = (BoolType) boolType.visit( this, null );

        if( valueNeeded )
            encoder.emit( Machine.LOADLop, 1, 0, Integer.parseInt(lit.getSpelling()));

        return null;
    }

    @Override
    public Object visitCharLiteral(CharLiteral CharLiteral, Object arg) {
        boolean valueNeeded = ((Boolean) arg).booleanValue();

        CharLiteral lit = (CharLiteral) CharLiteral.visit( this, null );

        if( valueNeeded )
            encoder.emit( Machine.LOADLop, 1, 0, Integer.parseInt(lit.getSpelling()));

        return null;
    }

    @Override
    public Object visitIdentifier(Identifier identifier, Object arg) {
        return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral integerLiteral, Object arg) {
        return new Integer( integerLiteral.getSpelling() );
    }

    @Override
    public Object visitOperator(Operator operator, Object arg) {
        return operator.getSpelling();
    }

}
