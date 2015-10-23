package pl.openpkw.dokument.generator;

public class DokumentGeneratorException extends RuntimeException {

    private static final long serialVersionUID = 5160561059125752581L;

    private DokumentGeneratorError error;
    
    private String additionalInfo;

    public DokumentGeneratorException(DokumentGeneratorError error, String additionalInfo) {
        super(error.getErrorMessage());
        this.error = error;
        this.additionalInfo = additionalInfo;
    }
    
    public DokumentGeneratorException(DokumentGeneratorError error) {
        this(error, null);
    }

    public DokumentGeneratorError getError() {
        return error;
    }
    
    public String getAdditionalInfo() {
        return additionalInfo;
    }
}