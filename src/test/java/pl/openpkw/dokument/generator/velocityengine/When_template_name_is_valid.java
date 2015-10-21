package pl.openpkw.dokument.generator.velocityengine;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.velocity.VelocityContext;
import org.junit.Test;

import pl.openpkw.dokument.generator.TestBase;
import pl.openpkw.dokument.generator.VelocityEngine;

public class When_template_name_is_valid extends TestBase {

    private VelocityEngine cut = new VelocityEngine();

    @Test
    public void should_return_PDF_document() {
        cut.initialize();
        String result = cut.process("/templates/test-template.html", new VelocityContext());
        assertThat(result, is(notNullValue()));
    }
}