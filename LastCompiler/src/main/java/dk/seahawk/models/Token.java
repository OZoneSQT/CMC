package dk.seahawk.models;

import static dk.seahawk.models.EToken.*;

public class Token {

    public EToken token;
    public String spelling;

    public Token(EToken eToken, String spelling) {
        this.token = eToken;
        this.spelling = spelling;

        if( eToken == EToken.IDENTIFIER ) {
            for (EToken tk : KEYWORDS) {
                if (spelling.equals(tk.getSpelling())) {
                    this.token = tk;
                    break;
                }
            }
        }
    }

    public boolean isAssignOperator() {
        if( token == OPERATOR ) { return containsOperator( spelling, ASSIGNOPS ); }
        else { return false; }
    }

    public boolean isAddOperator() {
        if( token == OPERATOR ) { return containsOperator( spelling, ADDOPS ); }
        else { return false; }
    }

    public boolean isMulOperator() {
        if( token == OPERATOR ) { return containsOperator( spelling, MULOPS ); }
        else { return false; }
    }

    private boolean containsOperator( String spelling, String OPS[] ) {
        for( int i = 0; i < OPS.length; ++i ) { if (spelling.equals(OPS[i])) return true; }
        return false;
    }

    private static final EToken[] KEYWORDS = { MAIN, FUNCTION, IF, ELSE, RETURN, PRINT };
    private static final String ASSIGNOPS[] = { ":=",};
    private static final String ADDOPS[] = { "+", "-" };
    private static final String MULOPS[] = { "*", "/", "%" };

}
