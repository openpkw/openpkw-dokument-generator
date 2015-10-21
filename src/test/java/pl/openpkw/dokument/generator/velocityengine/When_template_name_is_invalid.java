package pl.openpkw.dokument.generator.velocityengine;

import org.apache.velocity.VelocityContext;
import org.junit.Test;

import pl.openpkw.dokument.generator.DokumentGeneratorError;
import pl.openpkw.dokument.generator.TestBase;
import pl.openpkw.dokument.generator.VelocityEngine;

public class When_template_name_is_invalid extends TestBase {

    private VelocityEngine cut = new VelocityEngine();

    @Test
    public void should_return_TEMPLATE_NOT_FOUND_errorF() {
        run(() -> cut.process("invalid-template", new VelocityContext()), DokumentGeneratorError.TEMPLATE_NOT_FOUND);
    }
}