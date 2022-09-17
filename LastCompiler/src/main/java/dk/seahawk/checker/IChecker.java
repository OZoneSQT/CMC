package dk.seahawk.checker;

import dk.seahawk.parser.IParser;

public interface IChecker {
    void init(IParser parser);
    void check();
}
