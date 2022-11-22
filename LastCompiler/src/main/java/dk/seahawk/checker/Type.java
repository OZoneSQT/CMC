package dk.seahawk.checker;

public class Type {
    private boolean rvalueOnly;

    public Type( boolean rvalueOnly ) {
        this.rvalueOnly = rvalueOnly;
    }

    public boolean isRvalueOnly() {
        return rvalueOnly;
    }

}
