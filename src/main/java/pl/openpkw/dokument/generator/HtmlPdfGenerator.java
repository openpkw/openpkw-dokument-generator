package pl.openpkw.dokument.generator;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;

import pl.openpkw.dokument.generator.webservice.dto.Form;

@Singleton
public class HtmlPdfGenerator {

    @Inject
    private VelocityEngine velocity;

    @Inject
    private PdfRenderer pdfRenderer;

    @Inject
    private QRCodeGenerator qrCodeGenerator;

    /**
     * Tworzy dokument PDF na podstawie szablonu HTML oraz danych z formularza
     */
    public byte[] generate(Form formData) {
        try {
            Path workingDirectory = Files.createTempDirectory("openpkw-");
            Path htmlFile = generateHtmlFileFromTemplate("/templates/PdfTemplate.html", formData, workingDirectory);

            byte[] qrCode = generateQRCodeFromFormData(formData);
            saveFileInWorkingDirectory("qrcode.jpeg", workingDirectory, qrCode);

            copyFileToWorkingDirectory("/templates/styles.css", workingDirectory);
            return pdfRenderer.render(htmlFile);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to generate a PDF document: " + ex.getMessage(), ex);
        }
    }

    private Path generateHtmlFileFromTemplate(String velocityTemplatePath, Form formData, Path workingDirectory) {
        try {
            VelocityContext context = new VelocityContext();
            context.put("form", formData);
            String html = velocity.process(velocityTemplatePath, context);

            Path htmlFile = Files.createTempFile(workingDirectory, "form-", ".html");
            Files.write(htmlFile, html.getBytes("UTF-8"));

            return htmlFile;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to render html from Velocity template: " + ex.getMessage(), ex);
        }
    }

    private byte[] generateQRCodeFromFormData(Form formData) {
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
