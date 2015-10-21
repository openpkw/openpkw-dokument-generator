package pl.openpkw.dokument.generator.requestvalidator;

import org.junit.Test;

import pl.openpkw.dokument.generator.DokumentGeneratorError;
import pl.openpkw.dokument.generator.RequestValidator;
import pl.openpkw.dokument.generator.TestBase;

public class When_no_input_is_provided extends TestBase {

    private RequestValidator cut = new RequestValidator();

    @Test
    public void should_return_NO_DATA_error() {
        run(() -> cut.validate(null), DokumentGeneratorError.NO_DATA);
    }
}