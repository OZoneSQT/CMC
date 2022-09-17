package dk.seahawk;

import dk.seahawk.checker.Checker;
import dk.seahawk.checker.IChecker;
import dk.seahawk.generator.Generator;
import dk.seahawk.generator.IGenerator;
import dk.seahawk.models.EToken;
import dk.seahawk.models.Token;
import dk.seahawk.parser.IParser;
import dk.seahawk.parser.Parser;
import dk.seahawk.scanner.IScanner;
import dk.seahawk.scanner.Scanner;
import dk.seahawk.utils.SourceHandler;

import javax.swing.*;

public class Main {
    // private static final String EXAMPLES_DIR = "d:\\GitHub\\CMC\\LastCompiler\\src\\main\\java\\examplefiles";
    private static final String EXAMPLES_DIR = "e:\\GitHub\\CMC\\LastCompiler\\src\\main\\java\\examplefiles";

    public static void main(String args[]) throws InterruptedException {
        JFileChooser jFileChooser = new JFileChooser(EXAMPLES_DIR);

        /* JFileChooser = Dialog for selecting source file */
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            SourceHandler sourceHandler = new SourceHandler(jFileChooser.getSelectedFile().getAbsolutePath());

            IScanner scanner = new Scanner();
            scanner.init(sourceHandler);

            IParser parser = new Parser();
            parser.init(scanner);

            IChecker checker = new Checker();
            checker.init(parser);

            IGenerator generator = new Generator();
            generator.init(checker);

            Token token = scanner.scan();
            //parser.parseProgram();
            //checker.checkProgram();
            //generator.generateProgram();

            // Log to console
            while (token.token != EToken.EOT) {
                System.out.println(token.token + " " + token.spelling);
                token = scanner.scan();
            }
        }
    }

}