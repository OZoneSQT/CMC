package dk.seahawk.checker;

import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.parser.ast.declaration.Declaration;
import dk.seahawk.parser.ast.declaration.DeclarationList;
import dk.seahawk.parser.ast.declaration.FunctionDeclaration;
import dk.seahawk.parser.ast.declaration.VariableDeclaration;
import dk.seahawk.parser.ast.expression.*;
import dk.seahawk.parser.ast.statement.*;
import dk.seahawk.parser.ast.terminal.Block;
import dk.seahawk.parser.ast.terminal.Identifier;
import dk.seahawk.parser.ast.terminal.IntegerLiteral;
import dk.seahawk.parser.ast.terminal.Operator;
import dk.seahawk.scanner.Scanner;

import static dk.seahawk.models.EToken.*;


//TODO Look at Parser.java
public class ParserOperatorPrecedence {
        private Scanner scan;
        private Token currentTerminal;

	    public ParserOperatorPrecedence( Scanner scan ) {
            this.scan = scan;
            currentTerminal = scan.scan();
        }

        public Object parseProgram() {
            Block block = parseBlock();

            if( currentTerminal.token != EOT )
                System.out.println( "Tokens found after end of program" );

            return new Program( block );
        }

        private Block parseBlock() {
            accept( DECLARE );
            DeclarationList decs = parseDeclarations();
            accept( DO );
            StatementList stats = parseStatements();
            accept( OD );

            return new Block( decs, stats );
        }

        private DeclarationList parseDeclarations() {
            DeclarationList decs = new DeclarationList();

            while( currentTerminal.token == VAR ||
                    currentTerminal.token == FUNCTION )
                decs.declarations.add( parseOneDeclaration() );

            return decs;
        }

        private Declaration parseOneDeclaration() {
            switch( currentTerminal.token ) {
                case VAR:
                    accept( VAR );
                    Identifier id = parseIdentifier();
                    accept( SEMICOLON );

                    return new VariableDeclaration( id );

                case FUNCTION:
                    accept( FUNCTION );
                    Identifier name = parseIdentifier();
                    accept( LEFTPARAN );

                    DeclarationList params = null;
                    if( currentTerminal.token == IDENTIFIER)
                        params = parseIdList();
                    else
                        params = new DeclarationList();

                    accept( RIGHTPARAN );
                    Block block = parseBlock();
                    accept( RETURN );
                    Expression retExp = parseExpression();
                    accept( SEMICOLON );

                    return new FunctionDeclaration( name, params, block, retExp );

                default:
                    System.out.println( "var or func expected" );
                    return null;
            }
        }

        private DeclarationList parseIdList()
        {
            DeclarationList list = new DeclarationList();

            list.declarations.add( new VariableDeclaration( parseIdentifier() ) );

            while( currentTerminal.token == COMMA ) {
                accept( COMMA );
                list.declarations.add( new VariableDeclaration( parseIdentifier() ) );
            }

            return list;
        }

        private StatementList parseStatements()
        {
            StatementList stats = new StatementList();

            while( currentTerminal.token == IDENTIFIER ||
                    currentTerminal.token == OPERATOR ||
                    currentTerminal.token == INTEGERLITERAL ||
                    currentTerminal.token == LEFTPARAN ||
                    currentTerminal.token == IF ||
                    currentTerminal.token == WHILE ||
                    currentTerminal.token == PRINT )
                stats.statements.add( parseOneStatement() );

            return stats;
        }

        private Statement parseOneStatement()
        {
            switch( currentTerminal.token ) {
                case IDENTIFIER:
                case INTEGERLITERAL:
                case OPERATOR:
                case LEFTPARAN:
                    Expression exp = parseExpression();
                    accept( SEMICOLON );

                    return new ExpressionStatement( exp );

                case IF:
                    accept( IF );
                    Expression ifExp = parseExpression();
                    accept( THEN );
                    StatementList thenPart = parseStatements();

                    StatementList elsePart;
                    if( currentTerminal.token == ELSE ) {
                        accept( ELSE );
                        elsePart = parseStatements();
                    } else
                        elsePart = new StatementList();

                    accept( FI );
                    accept( SEMICOLON );

                    return new IfStatement( ifExp, thenPart, elsePart );

                case WHILE:
                    accept( WHILE );
                    Expression whileExp = parseExpression();
                    accept( DO );
                    StatementList stats = parseStatements();
                    accept( OD );
                    accept( SEMICOLON );

                    return new WhileStatement( whileExp, stats );

                case PRINT:
                    accept( PRINT );
                    Expression sayExp = parseExpression();
                    accept( SEMICOLON );

                    return new PrintStatement( sayExp );

                default:
                    System.out.println( "Error in statement" );
                    return null;
            }
        }

        private void accept(parser.TokenKind aWhile) {
    }

        private Expression parseExpression()
        {
            Expression res = parseExpression1();

            if( currentTerminal.isAssignOperator() ) {
                Operator op = parseOperator();
                Expression tmp = parseExpression();

                res = new BinaryExpression( op, res, tmp );
            }

            return res;
        }

        private Expression parseExpression1()
        {
            Expression res = parseExpression2();
            while( currentTerminal.isAddOperator() ) {
                Operator op = parseOperator();
                Expression tmp = parseExpression2();

                res = new BinaryExpression( op, res, tmp );
            }

            return res;
        }

        private Expression parseExpression2()
        {
            Expression res = parsePrimary();
            while( currentTerminal.isMulOperator() ) {
                Operator op = parseOperator();
                Expression tmp = parsePrimary();

                res = new BinaryExpression( op, res, tmp );
            }

            return res;
        }

        private Expression parsePrimary()
        {
            switch( currentTerminal.token ) {
                case IDENTIFIER:
                    Identifier name = parseIdentifier();

                    if( currentTerminal.token == LEFTPARAN ) {
                        accept( LEFTPARAN );

                        ExpressionList args;

                        if( currentTerminal.token == IDENTIFIER ||
                                currentTerminal.token == INTEGERLITERAL ||
                                currentTerminal.token == OPERATOR ||
                                currentTerminal.token == LEFTPARAN )

                            args = parseExpressionList();
                        else
                            args = new ExpressionList();


                        accept( RIGHTPARAN );

                        return new CallExpression( name, args );
                    } else
                        return new VarExpression( name );

                case INTEGERLITERAL:
                    IntegerLiteral lit = parseIntegerLiteral();
                    return new IntLitExpression( lit );

                case OPERATOR:
                    Operator op = parseOperator();
                    Expression exp = parsePrimary();
                    return new UnaryExpression( op, exp );

                case LEFTPARAN:
                    accept( LEFTPARAN );
                    Expression res = parseExpression();
                    accept( RIGHTPARAN );
                    return res;

                default:
                    System.out.println( "Error in primary" );
                    return null;
            }
        }

        private ExpressionList parseExpressionList()
        {
            ExpressionList expressionList = new ExpressionList();

            expressionList.expression.add( parseExpression() );
            while( currentTerminal.token == COMMA ) {
                accept( COMMA );
                expressionList.expression.add( parseExpression() );
            }

            return expressionList;
        }

        private Identifier parseIdentifier()
        {
            if( currentTerminal.token == IDENTIFIER ) {
                Identifier res = new Identifier( currentTerminal.spelling );
                currentTerminal = scan.scan();

                return res;
            } else {
                System.out.println( "Identifier expected" );

                return new Identifier( "???" );
            }
        }

        private IntegerLiteral parseIntegerLiteral()
        {
            if( currentTerminal.token == INTEGERLITERAL ) {
                IntegerLiteral res = new IntegerLiteral( currentTerminal.spelling );
                currentTerminal = scan.scan();

                return res;
            } else {
                System.out.println( "Integer literal expected" );

                return new IntegerLiteral( "???" );
            }
        }

        private Operator parseOperator()
        {
            if( currentTerminal.token == OPERATOR ) {
                Operator res = new Operator( currentTerminal.spelling );
                currentTerminal = scan.scan();

                return res;
            } else {
                System.out.println( "Operator expected" );

                return new Operator( "???" );
            }
        }

        private void accept( EToken expected ) {
            if( currentTerminal.token == expected )
                currentTerminal = scan.scan();
            else
                System.out.println( "Expected token of kind " + expected );
        }

    }