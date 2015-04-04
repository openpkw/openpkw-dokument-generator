package pl.openpkw.poc.backend.pdf;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import javax.inject.Singleton;

import org.jboss.vfs.VirtualFile;

/**
 * Zwraca ścieżkę do katalogu z fontami we wnętrzu aplikacji webowej rozproszonej na JBossie.
 * Zdaje się, że tylko JBoss wymaga takiego cudactwa.
 */
@Singleton
public class FontsManager {

    public File getFontsFolder() {
        try {
            URL fontsFolderName = this.getClass().getResource("/fonts");
            File fontsFolder = null;
            if (fontsFolderName.getProtocol().equals("vfs")) {
                URLConnection conn = fontsFolderName.openConnection();
                VirtualFile vf = (VirtualFile) conn.getContent();
                fontsFolder = vf.getPhysicalFile();
            } else {
                fontsFolder = new File(fontsFolderName.toURI());
            }

            return fontsFolder;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to get a list of font file names: " + ex.getMessage(), ex);
        }
    }
}
