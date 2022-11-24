package dk.seahawk;

import dk.seahawk.checker.Checker;
import dk.seahawk.checker.SemanticChecker;
import dk.seahawk.checker.SyntaxParserOperatorPrecedence;
import dk.seahawk.generator.Generator;
import dk.seahawk.generator.CodeGenerator;
import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.parser.ASTViewer;
import dk.seahawk.parser.ast.AST;
import dk.seahawk.parser.ast.Program;
import dk.seahawk.scanner.LexicalScanner;
import dk.seahawk.scanner.Scanner;
import dk.seahawk.utils.ErrorHandler;
import dk.seahawk.utils.IErrorHandler;
import dk.seahawk.utils.SourceHandler;

import javax.swing.*;

/**
 * Compiler for the programming language "Last" developed at
 * VIA University Collage, Horsens Denmark as a part of a course in "Compiler Construction"
 *
 * By Michel Sommer and Patrick Christiansen
 */

public class Main {
    //private static final String EXAMPLES_DIR = "d:\\GitHub\\CMC\\LastCompiler\\src\\main\\java\\examplefiles";
    private static final String EXAMPLES_DIR = "e:\\GitHub\\CMC\\LastCompiler\\src\\main\\java\\examplefiles";
    private static boolean debugMode = true;    //TODO set to true for debugging

    public static void main(String args[]) throws InterruptedException {
        JFileChooser jFileChooser = new JFileChooser(EXAMPLES_DIR);

        /* JFileChooser = Dialog for selecting source file */
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            SourceHandler sourceHandler = new SourceHandler(jFileChooser.getSelectedFile().getAbsolutePath());

            IErrorHandler errorHandler = new ErrorHandler();

            // Tokens, Lexical Analyzer
            /*
                It takes the output of the preprocessor (which performs file inclusion and macro expansion) as
                the input which is in a pure high-level language. It reads the characters from the source
                program and groups them into lexemes (sequence of characters that “go together”).
                Each lexeme corresponds to a token. Tokens are defined by regular expressions which are
                understood by the lexical analyzer. It also removes lexical errors
                (e.g., erroneous characters), comments, and white space.
             */
            LexicalScanner scanner = new Scanner(sourceHandler, errorHandler);

            // Grammar, Syntax Analyzer
            /*
                It is sometimes called a parser. It constructs the parse tree. It takes all the tokens one by
                one and uses Context-Free Grammar to construct the parse tree.

                Syntax error can be detected at this level if the input is not in accordance with the grammar.
             */
            //IParser parser = new Parser(scanner, errorHandler);
            //IParser parser = new ParserAST(scanner, errorHandler);
            SyntaxParserOperatorPrecedence parser = new SyntaxParserOperatorPrecedence( scanner );

            // Semantic Analyzer
            /*
                It verifies the parse tree, whether it’s meaningful or not. It furthermore produces a verified parse tree.
                It also does type checking, Label checking, and Flow control checking.
             */
            SemanticChecker checker = new Checker(errorHandler);

            // Code Generator
            CodeGenerator generator = new Generator(errorHandler);

            /*  Additional steps:
                - Code Optimization
                - Target Code Generator
             */

            //Program is used as a container for the program/code
            Program program = (Program) parser.parseProgram();
            Token token = scanner.scan();
            if ( debugMode ) consoleLogger(token, scanner);

            //AST ast = parser.parse();
            //printAST(ast);

            checker.check(program);
            generator.generate(program);

            boolean success = errorHandler.isSuccess();
            if ( debugMode ) {
                exportProgram(generator, jFileChooser);
            } else {
                if ( success ) exportProgram(generator, jFileChooser);
            }

            System.out.println("*** END *** ");
        }
    }

    // Log to console
    private static void consoleLogger(Token token, LexicalScanner scanner ) {
        while (token.token != EToken.EOT) {
            System.out.println(token.token + " " + token.spelling);
            token = scanner.scan();
        }
    }

    private static void printAST(AST ast) {
        new ASTViewer( ast );
    }

    // Get path and filename to output file
    private static void exportProgram(CodeGenerator generator, JFileChooser jFileChooser) {
        String sourceName = jFileChooser.getSelectedFile().getAbsolutePath();
        String targetName;
        if ( sourceName.endsWith(".txt") ) {
            targetName = sourceName.substring(4, sourceName.length() -4 ) + ".tam";
        } else {
            targetName = sourceName + ".tam";
        }

        generator.saveTargetProgram( targetName );
    }

}