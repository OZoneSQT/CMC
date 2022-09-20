package dk.seahawk.models;

public enum EToken {
    // literals, identifiers, operators...
    IDENTIFIER,
    INTEGERLITERAL,
    OPERATOR,

    // reserved words ...
    MAIN("main"),
    FUNCTION("function"),
    IF("if"),
    ELSE("else"),
    RETURN("return"),       //TODO How to
    PRINT("print"),

    // datatypes ...
    BOOL("bool"),
    CHAR("char"),
    INT("int"),
    ARRAY("arr"),           //TODO How to

    // punctuation...
    COMMA(","),
    DOT("."),
    SEMICOLON(";"),
    COLON(":"),

    // brackets...
    LEFTPARAN("("),
    RIGHTPARAN(")"),
    LEFTSQPARAN("["),
    RIGHTSQPARAN("]"),
    BEGINBLOCK("{"),
    ENDBLOCK("}"),
    SEPERATOR("\""),

    // special tokens...
    EOT,
    ERROR;


    private String spelling = null;

    private EToken() {
    }

    private EToken(String spelling) {
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }

}