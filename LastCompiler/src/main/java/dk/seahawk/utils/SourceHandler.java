package dk.seahawk.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SourceHandler {
    public static final char EOL = '\n';
    public static final char EOT = 0;
    private FileInputStream source;

    public SourceHandler( String sourceFileName ) throws InterruptedException {
        try {
            source = new FileInputStream( sourceFileName );
        } catch( FileNotFoundException ex ) {
            System.out.println( "*** FILE NOT FOUND *** (" + sourceFileName + ")" );
            ex.printStackTrace();
            Thread.sleep(4000);
            System.exit( 1 );
        } catch ( Exception e ) {
            System.out.println( "*** Error, accessing file ***" );
            Thread.sleep(10000);
            e.printStackTrace();
            System.exit( 1 );
        }
    }
//
    public char getSource() {
        try {
            int ch = source.read();
            if( ch < 0 ) { return EOT; }
            else { return (char) ch; }
        } catch( IOException ex ) {
            return EOT;
        }
    }

}