package dk.seahawk.models;

public enum EToken {
    IDENTIFIER,
    OPERATOR,

    MAIN( "main" ),
    FUNCTION( "function" ),
    IF( "if" ),
    ELSE( "else" ),
    RETURN( "return" ),
    PRINT( "print" ),

    BOOL( "bool" ),
    CHAR( "char" ),
    INT( "int" ),
    ARRAY( "arr" ),

    COMMA( "," ),
    SEMICOLON( ";" ),
    LEFTPARAN( "(" ),
    RIGHTPARAN( ")" ),
    LEFTSQPARAN( "[" ),
    RIGHTSQPARAN( "]" ),
    BEGINBLOCK("{"),
    ENDBLOCK("}"),
    SEPERATOR("\""), //TODO is this right?

    EOT,

    ERROR;


    private String spelling = null;

    private EToken() {}

    private EToken( String spelling ) {
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }

}