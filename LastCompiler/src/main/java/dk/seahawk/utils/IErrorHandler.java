package dk.seahawk.utils;

public interface IErrorHandler {
    void errorDetected(String errorDescription);
    boolean isSuccess();
}
