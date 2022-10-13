package dk.seahawk.ast.terminal;

import dk.seahawk.ast.AST;

public abstract class Terminal extends AST {
    private String spelling;

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }
}
