package dk.seahawk.parser.ast.terminal;

import dk.seahawk.parser.ast.AST;

public abstract class Terminal extends AST {
    private String spelling;

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

}
