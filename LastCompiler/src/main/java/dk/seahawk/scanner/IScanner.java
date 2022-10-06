package dk.seahawk.scanner;

import dk.seahawk.models.Token;
import dk.seahawk.utils.IErrorHandler;
import dk.seahawk.utils.SourceHandler;

public interface IScanner {
    Token scan();
}
