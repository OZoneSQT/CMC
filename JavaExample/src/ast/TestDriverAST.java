package ast;

import ast.ast.AST;
import javax.swing.*;

public class TestDriverAST {
	//private static final String EXAMPLES_DIR = "d:\\GitHub\\CMC\\JavaExample\\src\\example_files";
	private static final String EXAMPLES_DIR = "e:\\GitHub\\CMC\\JavaExample\\src\\example_files";

	public static void main( String args[] ) {
		JFileChooser fc = new JFileChooser( EXAMPLES_DIR );
		
		if( fc.showOpenDialog( null ) == fc.APPROVE_OPTION ) {
			SourceFile in = new SourceFile( fc.getSelectedFile().getAbsolutePath() );
			Scanner s = new Scanner( in );
			ParserAST p = new ParserAST( s );
		
			AST ast = p.parseProgram();
			
			new ASTViewer( ast );
		}
	}
}