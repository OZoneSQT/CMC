package dk.seahawk.utils;

import java.util.ArrayList;

public class ErrorHandler implements IErrorHandler {

    private boolean errorState = false;
    private ArrayList<String> errorList = new ArrayList<>();

    public ErrorHandler () {}

    @Override
    public void errorDetected(String errorDescription) {
        errorState = true;
        errorList.add(errorDescription);
    }

    private void printErrorList() {
        if (errorState == true) {
            System.out.println("Compiling is completed successfully...");
        } else {
            System.out.println("Compiling has failed with " + errorList.size() + 1 + " errors:");

            for (int i = 0; i <= errorList.size(); i++) {
                System.out.println( "Error " + i + ": " + errorList.get(i) );
            }

            System.exit( 1 );
        }
    }

    @Override
    public boolean isSuccess() {
        printErrorList();
        return errorState;
    }
}
