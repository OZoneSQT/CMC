package dk.seahawk.scanner;

import dk.seahawk.models.Token;
import dk.seahawk.utils.SourceHandler;

public interface IScanner {
    void scanSource(SourceHandler source );
    Token scan();

}
