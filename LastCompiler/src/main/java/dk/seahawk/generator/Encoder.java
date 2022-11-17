package dk.seahawk.generator;

import dk.seahawk.generator.tam.Instruction;
import dk.seahawk.generator.tam.Machine;
import dk.seahawk.utils.IErrorHandler;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Encoder {

    private IErrorHandler errorHandler;

    private int nextAdr = Machine.CB;
    private int currentLevel = 0;

    public int getNextAdr() {
        return nextAdr;
    }

    public void setNextAdr(int nextAdr) {
        this.nextAdr = nextAdr;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Encoder(IErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void saveTargetProgram(String targetName) {
        try {
            DataOutputStream out = new DataOutputStream( new FileOutputStream( targetName ) );

            for( int i = Machine.CB; i < nextAdr; ++i )
                Machine.code[i].write( out );

            out.close();
        } catch( Exception e ) {
            e.printStackTrace();
            System.out.println( "Trouble writing " + targetName );
        }
    }

    public void emit( int operator, int nWordSize, int register, int displacement) {
        if ( nWordSize > 255 )
            System.out.println( "Operand too long" );
        nWordSize = 255;

        Instruction instruction = new Instruction();
        instruction.op = operator;
        instruction.n = nWordSize;
        instruction.r = register;
        instruction.d = displacement;

        if ( nextAdr >= Machine.PB )
            System.out.println( "Program is to large" );
        else
            Machine.code[nextAdr++] = instruction;
    }

    public void patch( int address, int displacement ) {
        Machine.code[address].d = displacement;
    }

    public int displayRegister( int currentLevel, int entityLevel )
    {
        if( entityLevel == 0 )
            return Machine.SBr;
        else if( currentLevel - entityLevel <= 6 )
            return Machine.LBr + currentLevel - entityLevel;
        else {
            System.out.println( "Accessing across to many levels" );
            return Machine.L6r;
        }
    }

}
