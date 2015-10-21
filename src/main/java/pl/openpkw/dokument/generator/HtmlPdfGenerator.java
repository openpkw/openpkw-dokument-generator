package pl.openpkw.dokument.generator;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;

@Singleton
public class HtmlPdfGenerator {

    private VelocityEngine velocityEngine;

    private PdfRenderer pdfRenderer;

    private QRCodeGenerator qrCodeGenerator;

    private RequestValidator requestValidator;

    @Inject
    public HtmlPdfGenerator(VelocityEngine velocityEngine, PdfRenderer pdfRenderer, QRCodeGenerator qrCodeGenerator, RequestValidator requestValidator) {
        this.velocityEngine = velocityEngine;
        this.pdfRenderer = pdfRenderer;
        this.qrCodeGenerator = qrCodeGenerator;
        this.requestValidator = requestValidator;
    }

    /**
     * Tworzy dokument PDF na podstawie szablonu HTML oraz danych z formularza
     */
    public byte[] generate(Map<Object, Object> request) {
        requestValidator.validate(request);
        try {
            String templateName = (String) request.get("templateName");
            Map<Object, Object> formData = (Map<Object, Object>) request.get("formData");

            String templateFile = "/templates/" + templateName + ".html";
            Path workingDirectory = Files.createTempDirectory("openpkw-");
            Path htmlFile = generateHtmlFileFromTemplate(templateFile, formData, workingDirectory);

            byte[] qrCode = generateQRCodeFromFormData(formData);
            saveFileInWorkingDirectory("qrcode.jpeg", workingDirectory, qrCode);

            copyFileToWorkingDirectory("/templates/styles.css", workingDirectory);
            return pdfRenderer.render(htmlFile);
        } catch (DokumentGeneratorException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to generate a PDF document: " + ex.getMessage(), ex);
        }
    }

    private Path generateHtmlFileFromTemplate(String velocityTemplatePath, Map<Object, Object> formData, Path workingDirectory) {
        try {
            VelocityContext context = new VelocityContext();
            context.put("form", formData);
            String html = velocityEngine.process(velocityTemplatePath, context);

            Path htmlFile = Files.createTempFile(workingDirectory, "form-", ".html");
            Files.write(htmlFile, html.getBytes("UTF-8"));

            return htmlFile;
        } catch (DokumentGeneratorException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to render html from Velocity template: " + ex.getMessage(), ex);
        }
    }

    private byte[] generateQRCodeFromFormData(Map<Object, Object> formData) {
        String jsonData = qrCodeGenerator.createJsonDataForQRCode(formData);
        return qrCodeGenerator.generate(jsonData);
    }

    private void copyFileToWorkingDirectory(String filePath, Path workingDirectory) {
        try {
            InputStream sourceFile = this.getClass().getResourceAsStream(filePath);
            String targetFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            Path targetFilePath = Paths.get(workingDirectory.toString(), targetFileName);
            Files.copy(sourceFile, targetFilePath);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to copy file " + filePath + " to working directory: " + ex.getMessage(), ex);
        }
    }

    private void saveFileInWorkingDirectory(String targetFileName, Path workingDirectory, byte[] fileContent) {
        try {
            Path targetFilePath = Paths.get(workingDirectory.toString(), targetFileName);
            Files.write(targetFilePath, fileContent, StandardOpenOption.CREATE);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create file " + targetFileName + " in working directory: " + ex.getMessage(), ex);
        }
    }
}
