package dk.seahawk.parser;

import dk.seahawk.models.*;
import dk.seahawk.scanner.IScanner;

import static dk.seahawk.models.EToken.*;

public class Parser implements IParser {
    private IScanner scan;
    private Token currentTerminal;

    public Parser() {
    }

    public void init(IScanner scan) {
        this.scan = scan;
        currentTerminal = scan.scan();
    }


    //TODO implement methods for Grammar

    public void parseProgram() {
        parseBlock();
        if (currentTerminal.token != EOT) System.out.println("Tokens found after end of program");
    }

    private void parseBlock() {
        accept(DECLARE);
        parseDeclarations();
        accept(DO);
        parseStatements();
        accept(OD);
    }

    private void parseDeclarations() {
        while (currentTerminal.token == VAR ||
                currentTerminal.token == FUNCTION)
            parseOneDeclaration();
    }

    private void parseOneDeclaration() {
        switch (currentTerminal.token) {
            case VAR:
                accept(VAR);
                accept(IDENTIFIER);
                accept(SEMICOLON);
                break;

            case FUNCTION:
                accept(FUNCTION);
                accept(IDENTIFIER);
                accept(LEFTPARAN);

                if (currentTerminal.token == IDENTIFIER)
                    parseIdList();

                accept(RIGHTPARAN);
                parseBlock();
                accept(RETURN);
                parseExpression();
                accept(SEMICOLON);
                break;

            default:
                System.out.println("var or func expected");
                break;
        }
    }

    private void parseIdList() {
        accept(IDENTIFIER);

        while (currentTerminal.token == COMMA) {
            accept(COMMA);
            accept(IDENTIFIER);
        }
    }

    private void parseStatements() {
        while (currentTerminal.token == IDENTIFIER ||
                currentTerminal.token == OPERATOR ||
                currentTerminal.token == INTEGERLITERAL ||
                currentTerminal.token == LEFTPARAN ||
                currentTerminal.token == IF ||
                currentTerminal.token == WHILE ||
                currentTerminal.token == SAY)
            parseOneStatement();
    }

    private void parseOneStatement() {
        switch (currentTerminal.token) {
            case IDENTIFIER:
            case INTEGERLITERAL:
            case OPERATOR:
            case LEFTPARAN:
                parseExpression();
                accept(SEMICOLON);
                break;

            case IF:
                accept(IF);
                parseExpression();
                accept(THEN);
                parseStatements();

                if (currentTerminal.token == ELSE) {
                    accept(ELSE);
                    parseStatements();
                }

                accept(FI);
                accept(SEMICOLON);
                break;

            case WHILE:
                accept(WHILE);
                parseExpression();
                accept(DO);
                parseStatements();
                accept(OD);
                accept(SEMICOLON);
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

    private void parseExpression() {
        parsePrimary();
        while (currentTerminal.token == OPERATOR) {
            accept(OPERATOR);
            parsePrimary();
        }
    }

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


                    accept(RIGHTPARAN);
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

    private void parseExpressionList() {
        parseExpression();
        while (currentTerminal.token == COMMA) {
            accept(COMMA);
            parseExpression();
        }
    }

    private void accept(EToken expected) {
        if (currentTerminal.token == expected)
            currentTerminal = scan.scan();
        else
            System.out.println("Expected token of kind " + expected);
    }
}
