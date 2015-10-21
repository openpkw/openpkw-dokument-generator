package pl.openpkw.dokument.generator;

import java.util.Map;

public class RequestValidator {

    public void validate(Map<Object, Object> request) throws DokumentGeneratorException {
        if (request == null) {
            throw new DokumentGeneratorException(DokumentGeneratorError.NO_DATA);
        }

        String templateName = (String) request.get("templateName");
        if (templateName == null || templateName.trim().length() == 0) {
            throw new DokumentGeneratorException(DokumentGeneratorError.NO_TEMPLATE_NAME);
        }

        Map<Object, Object> formData = (Map<Object, Object>) request.get("formData");
        if (formData == null || formData.keySet().size() == 0) {
            throw new DokumentGeneratorException(DokumentGeneratorError.NO_FORM_DATA);
        }

        return;
    }
}