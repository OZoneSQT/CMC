package dk.seahawk.scanner;

import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.utils.SourceHandler;

import static dk.seahawk.models.EToken.*;

/** Tokens
 *
 *  Identifier	    ::= Letter ( Letter | Digit )*
 *  INTEGERLITERAL	::= Digit
 *  Operator		::= + | - | * | / | = | % | ^
 *  Letter			::= a | ... | z | A | ... | Z
 *  Digit		    ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 *
 *  Main
 *  function
 *  if
 *  else
 *  return
 *  print
 *
 *  char
 *  int
 *  bool
 *  array
 *
 *  ,
 *  ;
 *  :
 *  (
 *  )
 *  {
 *  }
 *  [
 *  ]
 *  “
 *  '
 */
public class Scanner implements IScanner {
    private SourceHandler source;
    private char currentChar;
    private StringBuffer currentSpelling = new StringBuffer();

    public Scanner() {
    }

    @Override
    public void init(SourceHandler source) {
        this.source = source;
        currentChar = source.getSource();
    }

    private void takeIt() {
        currentSpelling.append(currentChar);
        currentChar = source.getSource();
    }

    /* Sort bu ASCII value */
    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    /* Sort bu ASCII value */
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
        /*
            case ':':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return OPERATOR;
                } else
                    return ERROR;
        */

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
                takeIt();
                return SEPERATOR;

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