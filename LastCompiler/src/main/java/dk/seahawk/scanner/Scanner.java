package dk.seahawk.scanner;

import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.utils.IErrorHandler;
import dk.seahawk.utils.SourceHandler;

import static dk.seahawk.models.EToken.*;


public class Scanner implements LexicalScanner {
    private SourceHandler source;
    private IErrorHandler errorHandler;

    private char currentChar;
    private StringBuffer currentSpelling = new StringBuffer();

    public Scanner(SourceHandler source, IErrorHandler errorHandler) {
        this.source = source;
        this.errorHandler = errorHandler;
        currentChar = source.getSource();
    }

    private void takeIt() {
        currentSpelling.append(currentChar);
        currentChar = source.getSource();
    }

    /* "Sort" by ASCII value */
    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    /* "Sort" by ASCII value */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void scanSeparator() {
        /* Remove Comments */
        if (currentChar == '#') {
            takeIt();
            while (currentChar != SourceHandler.EOL && currentChar != SourceHandler.EOT) takeIt();
            if (currentChar == SourceHandler.EOL) takeIt();
        }

        /* Escape character, split for tree */
        //TODO What about space in text?
        if (currentChar == ' ' || currentChar == '\n' || currentChar == '\r' || currentChar == '\t') takeIt();
    }

    private EToken scanToken() {
        if( isLetter( currentChar ) ) {
            takeIt();
            while( isLetter( currentChar ) || isDigit( currentChar ) ) takeIt();
            return IDENTIFIER;
        } else if( isDigit( currentChar ) ) {
            takeIt();
            while( isDigit( currentChar ) ) takeIt();
            return INTEGERLITERAL;
        }

        switch (currentChar) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '^':
            case '=':
                takeIt();
                return OPERATOR;

            case ',':
                takeIt();
                return COMMA;

            case '.':
                takeIt();
                return DOT;

            case ';':
                takeIt();
                return SEMICOLON;

            case ':':
                takeIt();
                return COLON;

            case '(':
                takeIt();
                return LEFTPARAN;

            case ')':
                takeIt();
                return RIGHTPARAN;

            case '[':
                takeIt();
                return LEFTSQPARAN;

            case ']':
                takeIt();
                return RIGHTSQPARAN;

            case '{':
                takeIt();
                return BEGINBLOCK;

            case '}':
                takeIt();
                return ENDBLOCK;

            case '\"':
            case '\'':
                takeIt();
                return SEPERATOR;

            case SourceHandler.EOT:
                return EOT;

            default:
                takeIt();
                return ERROR;
        }
    }

    public Token scan() {
        while (currentChar == '#' ||
                currentChar == '\n' ||
                currentChar == '\r' /* carriage return */ ||
                currentChar == '\t' /* tab */ ||
                currentChar == ' ')
            scanSeparator();

        currentSpelling = new StringBuffer("");
        EToken kind = scanToken();

        return new Token(kind, new String(currentSpelling));
    }

}