package dk.seahawk;

import dk.seahawk.checker.Checker;
import dk.seahawk.checker.IChecker;
import dk.seahawk.generator.Generator;
import dk.seahawk.generator.IGenerator;
import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.parser.ASTViewer;
import dk.seahawk.parser.IParser;
import dk.seahawk.parser.ParserAST;
import dk.seahawk.parser.ast.AST;
import dk.seahawk.scanner.IScanner;
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
            IScanner scanner = new Scanner(sourceHandler, errorHandler);

            // Grammar, Syntax Analyzer
            /*
                It is sometimes called a parser. It constructs the parse tree. It takes all the tokens one by
                one and uses Context-Free Grammar to construct the parse tree.

                Syntax error can be detected at this level if the input is not in accordance with the grammar.
             */
            //IParser parser = new Parser(scanner, errorHandler);
            //TODO redirect to ASTParser
            IParser parser = new ParserAST(scanner, errorHandler);

            // Semantic Analyzer
            /*
                It verifies the parse tree, whether it’s meaningful or not. It furthermore produces a verified parse tree.
                It also does type checking, Label checking, and Flow control checking.
             */
            IChecker checker = new Checker(parser, errorHandler);

            // Code Generator
            IGenerator generator = new Generator(checker, errorHandler);

            /*  Additional steps:
                - Code Optimization
                - Target Code Generator
             */

            Token token = scanner.scan();
            AST ast = parser.parse();
            checker.check();
            generator.generate();

            consoleLogger(token, scanner);
            printAST(ast);

            System.out.println("*** END *** ");

        }
    }

    // Log to console
    private static void consoleLogger(Token token, IScanner scanner ) {
        while (token.token != EToken.EOT) {
            System.out.println(token.token + " " + token.spelling);
            token = scanner.scan();
        }
    }

    private static void printAST(AST ast) {
        new ASTViewer( ast );
    }

}