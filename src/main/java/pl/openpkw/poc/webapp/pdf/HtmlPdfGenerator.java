package pl.openpkw.poc.webapp.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import pl.openpkw.poc.webapp.VelocityEngine;
import pl.openpkw.poc.webapp.domain.Formularz;

import com.lowagie.text.pdf.BaseFont;

@Singleton
public class HtmlPdfGenerator implements IPdfGenerator {

    @Inject
    private VelocityEngine velocity;

    @Inject
    private FontsManager fontsManager;

    public byte[] generate(Formularz form) {
        try {
            VelocityContext context = new VelocityContext();
            context.put("form", form);

            String html = velocity.process("/templates/pdf_template.html", context);
            File htmlFile = File.createTempFile("openpkw-" + Calendar.getInstance().getTimeInMillis(), ".html");
            String tempDirectory = htmlFile.getParent();
            FileOutputStream out = new FileOutputStream(htmlFile);
            out.write(html.getBytes("UTF-8"));
            out.close();

            try {
                Files.copy(this.getClass().getResourceAsStream("/templates/styles.css"), FileSystems.getDefault().getPath(tempDirectory + "/styles.css"), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ex) {
                System.out.println("Warning: Could not overwrite styles.css. This file may be obsolete.");
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();

            File fontsFolder = fontsManager.getFontsFolder();
            String fontsFolderPath = fontsFolder.getAbsolutePath() + File.separatorChar;
            String[] getFontFileNames = fontsFolder.list();

            for (String fontName : getFontFileNames) {
                String fontFullPath = fontsFolderPath + fontName;
                System.out.println("Loading font " + fontFullPath);
                renderer.getFontResolver().addFont(fontFullPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            }

            renderer.setDocument(htmlFile);
            renderer.layout();
            renderer.createPDF(os);
            os.close();
            byte[] result = os.toByteArray();

            try {
                Files.delete(FileSystems.getDefault().getPath(tempDirectory + "/styles.css"));
            } catch (Exception ex) {
                System.out.println("Warning: Could not delete styles.css");
            }

            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to generate a PDF document: " + ex.getMessage(), ex);
        }
    }
}