package pl.openpkw.dokument.generator.requestvalidator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pl.openpkw.dokument.generator.DokumentGeneratorError;
import pl.openpkw.dokument.generator.RequestValidator;
import pl.openpkw.dokument.generator.TestBase;

public class When_template_name_is_not_provided extends TestBase {

    private RequestValidator cut = new RequestValidator();

    @Test
    public void should_return_NO_TEMPLAE_NAME_error_if_template_name_is_null() {
        run(() -> cut.validate(new HashMap<Object, Object>()), DokumentGeneratorError.NO_TEMPLATE_NAME);
    }

    @Test
    public void should_return_NO_TEMPLAE_NAME_error_if_template_name_is_an_empty_string() {
        Map<Object, Object> input = new HashMap<>();
        input.put("templateName", "");
        run(() -> cut.validate(input), DokumentGeneratorError.NO_TEMPLATE_NAME);
    }
}