package dk.seahawk.models;

public enum EToken {
    IDENTIFIER,
    INTEGERLITERAL,
    OPERATOR,

    MAIN("main"),
    FUNCTION("function"),
    IF("if"),
    ELSE("else"),
    RETURN("return"),
    PRINT("print"),

    BOOL("bool"),
    CHAR("char"),
    INT("int"),
    ARRAY("arr"),

    COMMA(","),
    DOT("."),
    SEMICOLON(";"),
    COLON(":"),
    LEFTPARAN("("),
    RIGHTPARAN(")"),
    LEFTSQPARAN("["),
    RIGHTSQPARAN("]"),
    BEGINBLOCK("{"),
    ENDBLOCK("}"),
    SEPERATOR("\""),

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