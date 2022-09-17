package dk.seahawk.parser;

import dk.seahawk.scanner.IScanner;

public interface IParser {
    void init(IScanner scan);
    void parse();
}
