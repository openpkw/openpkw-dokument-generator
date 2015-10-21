package pl.openpkw.dokument.generator.requestvalidator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pl.openpkw.dokument.generator.DokumentGeneratorError;
import pl.openpkw.dokument.generator.RequestValidator;
import pl.openpkw.dokument.generator.TestBase;

public class When_form_data_is_not_provided extends TestBase {

    private RequestValidator cut = new RequestValidator();

    @Test
    public void should_return_NO_FORM_DATA_error_if_form_data_is_null() {
        Map<Object, Object> input = new HashMap<Object, Object>();
        input.put("templateName", "test-template");
        run(() -> cut.validate(input), DokumentGeneratorError.NO_FORM_DATA);
    }

    @Test
    public void should_return_NO_FORM_DATA_error_if_form_data_is_empty() {
        Map<Object, Object> input = new HashMap<>();
        input.put("templateName", "test-template");
        input.put("formData", new HashMap<Object, Object>());
        run(() -> cut.validate(input), DokumentGeneratorError.NO_FORM_DATA);
    }
}