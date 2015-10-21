package pl.openpkw.dokument.generator;

import junit.framework.Assert;

public class TestBase {
    
    protected void run(Runnable runnable, DokumentGeneratorError expectedError) {
        try {
            runnable.run();
            Assert.fail("Error '" + expectedError.getErrorMessage() + "' was expected but no errors occurred.");
        } catch (DokumentGeneratorException ex) {
            Assert.assertEquals(expectedError, ex.getError());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Error " + expectedError.getErrorMessage() + " was expected, but " + ex.getMessage() + " has been thrown.");
        }
    }
}