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
import dk.seahawk.parser.ast.terminal.*;
import dk.seahawk.scanner.IScanner;
import dk.seahawk.scanner.Scanner;

import static dk.seahawk.models.EToken.*;

public class ParserOperatorPrecedence {
    private Scanner scan;
    private Token currentTerminal;

    public ParserOperatorPrecedence(IScanner scanner) {
        this.scan = (Scanner) scanner;
        currentTerminal = scanner.scan();
    }

    public Object parseProgram() {
        Block block = parseBlock();

        //TODO Check upon this content imported after EOT
        if( currentTerminal.token != EOT ) System.out.println( "Tokens found after end of program" );

        return new Program( block );
    }

    private Block parseBlock() {
            if (currentTerminal.token == BEGINBLOCK) accept(BEGINBLOCK);

            DeclarationList declarationList = parseDeclarations();
            StatementList statementList = parseStatements();

            if (currentTerminal.token == ENDBLOCK) accept(ENDBLOCK);

            return new Block(declarationList, statementList);
        }

    private DeclarationList parseDeclarations() {
            DeclarationList declarationList = new DeclarationList();

            if (currentTerminal.token == BOOL ||
                    currentTerminal.token == CHAR ||
                    currentTerminal.token == INT ||
                    currentTerminal.token == ARRAY ||
                    currentTerminal.token == FUNCTION ) {
                declarationList.declarations.add( parseOneDeclaration() );
            }
            return declarationList;
        }

    private Declaration parseOneDeclaration() {
            switch (currentTerminal.token) {
                case BOOL:
                case CHAR:
                case INT:
                case ARRAY:
                    Identifier id = parseIdentifier();
                    return new VariableDeclaration( id );

                case FUNCTION:
                    accept(FUNCTION);
                    Identifier identifier = parseIdentifier();
                    accept(LEFTPARAN);

                    DeclarationList declarationList = null;
                    if (currentTerminal.token == IDENTIFIER)
                        declarationList = parseIdentifierList();
                    else
                        declarationList = new DeclarationList();

                    accept(RIGHTPARAN);
                    Block block = parseBlock();

                    if (currentTerminal.token == RETURN) accept(RETURN);

                    Expression expression = parseExpression();
                    accept(SEMICOLON);
                    return new FunctionDeclaration( identifier, declarationList, block, expression );

                default:
                    System.out.println("*** Variable or function expected ***");
                    return null;
            }
        }

    private DeclarationList parseIdentifierList() {
            DeclarationList declarationList = new DeclarationList();

            while (currentTerminal.token == COMMA) {
                accept(COMMA);
                accept(IDENTIFIER);
                declarationList.declarations.add( new VariableDeclaration( parseIdentifier() ) );
            }
            return declarationList;
        }

    private DeclarationList parseIdList() {
            DeclarationList list = new DeclarationList();

            list.declarations.add( new VariableDeclaration( parseIdentifier() ) );

            while( currentTerminal.token == COMMA ) {
                accept( COMMA );
                list.declarations.add( new VariableDeclaration( parseIdentifier() ) );
            }

            return list;
        }

    // Primary ::= OneStatement*
    private StatementList parseStatements() {
        StatementList statementList = new StatementList();
        while (currentTerminal.token == IDENTIFIER ||
                currentTerminal.token == OPERATOR ||
                currentTerminal.token == INTEGERLITERAL ||
                currentTerminal.token == LEFTPARAN ||
                currentTerminal.token == IF ||
                currentTerminal.token == PRINT) {
            parseOneStatement();
        }

        return statementList;
    }

    // Look at Token list
    // OneStatement ::= Expression ; | if (Expression) { Statements }  ( ε | else { Statements }  ) | print ( Expression )
    private Statement parseOneStatement() {
        switch (currentTerminal.token) {
            case IDENTIFIER:
            case INTEGERLITERAL:
            case OPERATOR:
            case LEFTPARAN:
                Expression exp = parseExpression();
                accept(SEMICOLON);  //TODO

                return new ExpressionStatement( exp );

            // Can be condition = true
            case IF:
                accept(IF);
                accept(BEGINBLOCK);
                Expression ifExp = parseExpression();
                accept(ENDBLOCK);

                //TODO Why is ELSE parsing without IF
                StatementList elseExp = null;
                if (currentTerminal.token == ELSE) {
                    accept(ELSE);
                    accept(BEGINBLOCK);
                    elseExp = parseStatements();
                    accept(ENDBLOCK);
                } else {
                    elseExp = new StatementList();
                }

                return new IfStatement( ifExp, elseExp );

            case PRINT:
                accept(PRINT);
                Expression printExp = parseExpression();
                accept( SEMICOLON );

                return new PrintStatement( printExp );

            default:
                System.out.println("Error in statement");
                return null;
        }
    }

    // Expression ::= Primary (Operator Primary)*
    private Expression parseExpression() {
        Expression expression = parseExpression1();
        while( currentTerminal.token == OPERATOR ) {
            Operator operator = parseOperator();
            Expression primary = parsePrimary();

            expression = new BinaryExpression( operator, expression, primary );
        }

        return expression;
    }

    private Expression parseExpression1() {
        Expression expression = parseExpression2();

        if( currentTerminal.isAssignOperator() ) {
            Operator operator = parseOperator();
            Expression tempExpression = parseExpression();

            expression = new BinaryExpression( operator, expression, tempExpression );
        }

        return expression;
    }

    private Expression parseExpression2() {
        Expression expression = parsePrimary();
        while( currentTerminal.isMulOperator() ) {
            Operator operator = parseOperator();
            Expression tempExpression = parsePrimary();

            expression = new BinaryExpression( operator, expression, tempExpression );
        }

        return expression;
    }

    // Expression ::= Identifier ( ε | ExpressionList ) | Operator Primary | IntegerLiteral | ( Expression )
    private Expression parsePrimary() {
        switch (currentTerminal.token) {
            case IDENTIFIER:
                Identifier name = parseIdentifier();

                if( currentTerminal.token == LEFTPARAN ) {
                    accept( LEFTPARAN );

                    ExpressionList expressionList;

                    if( currentTerminal.token == IDENTIFIER ||
                            currentTerminal.token == INTEGERLITERAL ||
                            currentTerminal.token == OPERATOR ||
                            currentTerminal.token == LEFTPARAN )

                        expressionList = parseExpressionList();
                    else
                        expressionList = new ExpressionList();

                    accept( RIGHTPARAN );

                    return new CallExpression( name, expressionList );
                }

            case INTEGERLITERAL:
                IntegerLiteral integerLiteral = parseInteger();
                return new IntegerExpression( integerLiteral );

            case CHAR:
                CharLiteral charLiteral = parseChar();
                return new CharExpression( charLiteral );

            case BOOL:
                BoolType boolType = parseBool();
                return new BoolExpression(boolType);

            case OPERATOR:
                Operator operator = parseOperator();
                Expression expression = parsePrimary();
                return new UnaryExpression( operator, expression);

            case LEFTPARAN:
                accept(LEFTPARAN);
                Expression exp = parseExpression();
                accept(RIGHTPARAN);
                return exp;

            default:
                System.out.println("Error in primary");
                return null;
        }
    }

    // ExpressionList ::= Expression(, Expression)*
    private ExpressionList parseExpressionList() {
        ExpressionList expressionList = new ExpressionList();

        expressionList.expression.add( parseExpression() );
        while (currentTerminal.token == COMMA) {
            accept(COMMA);      // Separator
            expressionList.expression.add( parseExpression() );
        }

        return expressionList;
    }

    private Identifier parseIdentifier() {
        if( currentTerminal.token == IDENTIFIER ) {
            Identifier identifier = new Identifier( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return identifier;
        } else {
            System.out.println( "Identifier expected" );

            return new Identifier( "???" );
        }
    }

    private IntegerLiteral parseInteger() {
        if( currentTerminal.token == INTEGERLITERAL ) {
            IntegerLiteral integerLiteral = new IntegerLiteral( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return integerLiteral;
        } else {
            System.out.println( "Integer literal expected" );

            return new IntegerLiteral( "???" );
        }
    }

    private BoolType parseBool() {
        if( currentTerminal.token == BOOL && ( currentTerminal.spelling == "true" || currentTerminal.spelling == "false" ) ) {
            BoolType boolType = new BoolType( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return boolType;
        } else {
            System.out.println( "Boolean literal expected" );

            return new BoolType( "???" );
        }
    }

    private CharLiteral parseChar() {
        if( currentTerminal.token == CHAR ) {
            CharLiteral charLiteral = new CharLiteral( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return charLiteral;
        } else {
            System.out.println( "Integer literal expected" );

            return new CharLiteral( "???" );
        }
    }

    private Operator parseOperator() {
        if( currentTerminal.token == OPERATOR ) {
            Operator operator = new Operator( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return operator;
        } else {
            System.out.println( "Operator expected" );

            return new Operator( "???" );
        }
    }

    // Expected terminal (EToken)
    private void accept(EToken expected) {
        if (currentTerminal.token == expected) {
            currentTerminal = scan.scan();
        } else {
            System.out.println("Expected token of kind " + expected);
        }
    }

}