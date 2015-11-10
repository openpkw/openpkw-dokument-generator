package pl.openpkw.dokument.generator;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

@Singleton
public class VelocityEngine {

    @PostConstruct
    public void initialize() {
        Properties velocityProperties = new Properties();
        velocityProperties.setProperty("resource.loader", "classpath");
        velocityProperties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityProperties.setProperty("input.encoding", "UTF-8");
        velocityProperties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
        velocityProperties.setProperty("runtime.log.logsystem.log4j.category", "velocity");
        velocityProperties.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
        Velocity.init(velocityProperties);
    }

    public String process(String templateName, VelocityContext context) {
        try {
            Template template = Velocity.getTemplate(templateName);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            template.merge(context, outWriter);
            outWriter.close();
            String result = new String(out.toByteArray(), "UTF-8");
            result = escapeHtmlEntities(result);
            return result;
        } catch (ResourceNotFoundException ex) {
            ex.printStackTrace();
            throw new DokumentGeneratorException(DokumentGeneratorError.TEMPLATE_NOT_FOUND, templateName);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to process a Velocity template " + templateName + ": " + ex.getMessage(), ex);
        }
    }

    private String escapeHtmlEntities(String input) {
        return input.replace((CharSequence) "&", "&amp;");
    }
}