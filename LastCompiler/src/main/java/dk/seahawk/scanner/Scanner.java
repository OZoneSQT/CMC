package dk.seahawk.scanner;

import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.utils.SourceHandler;

import static dk.seahawk.models.EToken.*;

public class Scanner implements IScanner {
    private SourceHandler source;
    private char currentChar;
    private StringBuffer currentSpelling = new StringBuffer();

    public Scanner() {}

    @Override
    public void scanSource(SourceHandler source) {
        this.source = source;
        currentChar = source.getSource();
    }

    private void takeIt() {
        currentSpelling.append( currentChar );
        currentChar = source.getSource();
    }

    /* Sort bu ASCII value */
    private boolean isLetter( char c ) { return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'; }

    /* Sort bu ASCII value */
    private boolean isDigit( char c ) { return c >= '0' && c <= '9'; }

    private void scanSeparator() {
        /* Remove Comments */
        if(currentChar == '#') {
            takeIt();
            while( currentChar != SourceHandler.EOL && currentChar != SourceHandler.EOT ) takeIt();
            if( currentChar == SourceHandler.EOL ) takeIt();
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
        } else if (currentChar == '+' || currentChar == '-' || currentChar == '/' || currentChar == '*' || currentChar == '%' || currentChar == '^' || currentChar == '=') {
            takeIt();
            return OPERATOR;
        } else if (currentChar == ',') { takeIt(); return COMMA; }
        else if (currentChar == ';') { takeIt(); return SEMICOLON; }
        else if (currentChar == '(') { takeIt(); return LEFTPARAN; }
        else if (currentChar == ')') { takeIt(); return RIGHTPARAN; }
        else if (currentChar == '[') { takeIt(); return LEFTSQPARAN; }
        else if (currentChar == ']') { takeIt(); return RIGHTSQPARAN; }
        else if (currentChar == '{') { takeIt(); return BEGINBLOCK; }
        else if (currentChar == '}') { takeIt(); return ENDBLOCK; }
        else if  (currentChar == '\"') { takeIt(); return SEPERATOR; } //TODO Is this right?
        else if (currentChar == '\'') { takeIt(); return SEPERATOR; } //TODO Is this right?
        else { takeIt(); return ERROR;  }
    }

    public Token scan() {
        while( currentChar == '#' ||
                currentChar == '\n' ||
                currentChar == '\r' /* carriage return */ ||
                currentChar == '\t' /* tab */ ||
                currentChar == ' ' )
            scanSeparator();

        currentSpelling = new StringBuffer( "" );
        EToken token = scanToken();

        return new Token( token, new String( currentSpelling ) );
    }

}

