package pl.openpkw.dokument.generator;

public class DokumentGeneratorException extends RuntimeException {

    private static final long serialVersionUID = 5160561059125752581L;

    private DokumentGeneratorError error;

    public DokumentGeneratorException(DokumentGeneratorError error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public DokumentGeneratorError getError() {
        return error;
    }
}