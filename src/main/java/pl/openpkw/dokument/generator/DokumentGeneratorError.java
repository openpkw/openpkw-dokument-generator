package pl.openpkw.dokument.generator;

public enum DokumentGeneratorError {

    OK(0, "OK"),
    
    NO_DATA(101, "No data found"),
    
    NO_TEMPLATE_NAME(201, "Template name not provided"),
    TEMPLATE_NOT_FOUND(202, "Template not found"),
    
    NO_FORM_DATA(301, "Form data not provided");

    String errorMessage;
    int errorCode;

    DokumentGeneratorError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
