package dk.seahawk.parser;

import dk.seahawk.models.*;
import dk.seahawk.scanner.IScanner;
import dk.seahawk.utils.IErrorHandler;

import static dk.seahawk.models.EToken.*;

/**
 * Grammar
 *
 * Program		        ::= Block
 * Block 		        ::= DeclarationList { Statements }
 * DeclarationList	    ::= OneDeclaration*
 *                          | DeclarationList OneDeclaration
 * OneDeclaration	    ::= Type Identifier
 *                          | function Identifier(IdentifierList)
 * Type 		        ::= bool
 *                          | int
 *                          | char
 *                          | ObjectList
 * ObjectList		    ::= Object*
 * Object		        ::= array Identifier [ IntegerLiteral ]
 *                          | Block
 *                          | Object
 * IdentifierList	    ::= Identifier*
 * Expression		    ::= Primary
 *                          | Expression Operator Primary
 * Primary		        ::= Identifier ( ε | ExpressionList )
 *                          | Operator Primary
 *                          | IntegerLiteral
 *                          | ( Expression )
 * Statements		    ::= OneStatement*
 * OneStatement	        ::= Expression;
 *                          | if (Expression) { Statements } ( ε | else { Statements } )
 *                          | print ( Expression )
 * ExpressionList		::= Expression(, Expression)*
 */

// Recursive Descent Parser
public class Parser implements IParser {
    private IScanner scan;
    private IErrorHandler errorHandler;
    private Token currentTerminal;

    public Parser(IScanner scan, IErrorHandler errorHandler) {
        this.scan = scan;
        this.errorHandler = errorHandler;
    }

    public void parse() {
        currentTerminal = scan.scan();
        parseProgram();
    }

    // Program ::= Block
    private void parseProgram() {
        parseBlock();
       // if (currentTerminal.token != EOT) System.out.println("Tokens found after end of program");
    }

    // Block ::= DeclarationList { Statements }
    private void parseBlock() {

        if (currentTerminal.token == BEGINBLOCK) accept(BEGINBLOCK);

        parseDeclarationList();
        parseStatements();

        if (currentTerminal.token == ENDBLOCK) accept(ENDBLOCK);

    }

    // DeclarationList ::= OneDeclaration*
    private void parseDeclarationList() {
        if (currentTerminal.token == BOOL ||
                currentTerminal.token == CHAR ||
                currentTerminal.token == INT ||
                currentTerminal.token == ARRAY ||
                currentTerminal.token == FUNCTION ) {
            parseOneDeclaration();
        }
    }

    // OneDeclaration ::= Type Identifier | function ( IdentifierList )
    private void parseOneDeclaration() {
        switch (currentTerminal.token) {
            case BOOL:
            case CHAR:
            case INT:
            case ARRAY:
                parseType();
                break;

            case FUNCTION:
                accept(FUNCTION);
                accept(IDENTIFIER);
                accept(LEFTPARAN);

                if (currentTerminal.token == IDENTIFIER) parseIdentifierList();

                accept(RIGHTPARAN);
                parseBlock();

                if (currentTerminal.token == RETURN) accept(RETURN);

                parseExpression();
                accept(SEMICOLON);
                break;

            default:
                System.out.println("*** Variable or function expected ***");
                break;
        }
    }

    // Type ::= bool | int | char | ObjectList
    private void parseType() {
        switch (currentTerminal.token) {
            case BOOL:
                accept(BOOL);
                accept(IDENTIFIER);
                accept(SEMICOLON);
                break;

            case CHAR:
                accept(CHAR);
                accept(IDENTIFIER);
                accept(SEMICOLON);
                break;

            case INT:
                accept(INT);
                accept(IDENTIFIER);
                accept(SEMICOLON);
                break;

            case ARRAY:
                accept(ARRAY);
                accept(IDENTIFIER);
                accept(LEFTSQPARAN);
                accept(INTEGERLITERAL);     // length
                accept(RIGHTSQPARAN);
                accept(SEMICOLON);
                break;

            default:
                System.out.println("*** Variable expected ***");
                break;
        }
    }

    private void parseIdentifierList() {
        accept(IDENTIFIER);

        while (currentTerminal.token == COMMA) {
            accept(COMMA);
            accept(IDENTIFIER);
        }
    }


    // Expression ::= Primary (Operator Primary)*
    private void parseExpression() {
        parsePrimary();
        while (currentTerminal.token == IDENTIFIER) {
            if (currentTerminal.token == OPERATOR) accept(OPERATOR);
            accept(OPERATOR);   //TODO
            parsePrimary();
        }
    }

    // ExpressionList ::= Expression(, Expression)*
    private void parseExpressionList() {
        parseExpression();
        while (currentTerminal.token == COMMA) {
            accept(COMMA);      // Separator
            parseExpression();
        }
    }

    // Expression ::= Identifier ( ε | ExpressionList ) | Operator Primary | IntegerLiteral | ( Expression )
    private void parsePrimary() {
        switch (currentTerminal.token) {
            case IDENTIFIER:
                accept(IDENTIFIER);

                if (currentTerminal.token == LEFTPARAN) {
                    accept(LEFTPARAN);

                    if (currentTerminal.token == IDENTIFIER ||
                            currentTerminal.token == INTEGERLITERAL ||
                            currentTerminal.token == OPERATOR ||
                            currentTerminal.token == LEFTPARAN)
                        parseExpressionList();

                    accept(RIGHTPARAN);     //TODO Why does this not catch closing parentheses if empty ex. Main()
                }
                break;

            case INTEGERLITERAL:
                accept(INTEGERLITERAL);
                break;

            case OPERATOR:
                accept(OPERATOR);
                parsePrimary();
                break;

            case LEFTPARAN:
                accept(LEFTPARAN);
                parseExpression();
                accept(RIGHTPARAN);
                break;

            default:
                System.out.println("Error in primary");
                break;
        }
    }

    // Primary ::= OneStatement*
    private void parseStatements() {
        while (currentTerminal.token == IDENTIFIER ||
                currentTerminal.token == OPERATOR ||
                currentTerminal.token == INTEGERLITERAL ||
                currentTerminal.token == LEFTPARAN ||
                currentTerminal.token == IF ||
                currentTerminal.token == PRINT) {
            parseOneStatement();
        }
    }

    // Look at Token list
    // OneStatement ::= Expression ; | if (Expression) { Statements }  ( ε | else { Statements }  ) | print ( Expression )
    private void parseOneStatement() {
        switch (currentTerminal.token) {
            case IDENTIFIER:
            case INTEGERLITERAL:
            case OPERATOR:
            case LEFTPARAN:
                parseExpression();
                //accept(SEMICOLON);  //TODO
                break;

            // Can be condition = true
            case IF:
                accept(IF);
                parseExpression();
                accept(BEGINBLOCK);
                parseStatements();
                accept(ENDBLOCK);

                //TODO Why is ELSE parsing without IF
                if (currentTerminal.token == ELSE) {
                    accept(ELSE);
                    accept(BEGINBLOCK);
                    parseStatements();
                    accept(ENDBLOCK);
                }

                break;

            case PRINT:
                accept(PRINT);
                parseExpression();
                accept(SEMICOLON);
                break;

            default:
                System.out.println("Error in statement");
                break;
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