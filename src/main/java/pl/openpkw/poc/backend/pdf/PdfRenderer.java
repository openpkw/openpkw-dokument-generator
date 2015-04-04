package pl.openpkw.poc.backend.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

/**
 * Proxy dla ITextRenderera, którego zadaniem jest załadowanie fontów na starcie, a także uproszczenie
 * wywoływania funkcji renderowania.
 */
@Singleton
public class PdfRenderer {

    private final static Logger log = Logger.getLogger(PdfRenderer.class);

    @Inject
    private FontsManager fontsManager;

    private ITextRenderer renderer;

    @PostConstruct
    public void configureITextRenderer() {
        log.debug("Configuring ITextRenderer");

        try {
            renderer = new ITextRenderer();

            File fontsFolder = fontsManager.getFontsFolder();
            String fontsFolderPath = fontsFolder.getAbsolutePath() + File.separatorChar;
            String[] getFontFileNames = fontsFolder.list();

            for (String fontName : getFontFileNames) {
                String fontFullPath = fontsFolderPath + fontName;
                log.debug("Loading font " + fontFullPath);
                renderer.getFontResolver().addFont(fontFullPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to configure PDF renderer: " + ex.getMessage(), ex);
        }
    }

    public byte[] render(Path htmlFile) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            renderer.setDocument(htmlFile.toFile());
            renderer.layout();
            renderer.createPDF(os);
            os.close();
            return os.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to render PDF file from HTML source: " + ex.getMessage(), ex);
        }
    }
}