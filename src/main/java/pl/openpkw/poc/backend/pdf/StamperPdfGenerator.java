package pl.openpkw.poc.backend.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.DecimalFormat;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.openpkw.poc.backend.annotations.FormElement;
import pl.openpkw.poc.backend.domain.Formularz;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * Prawdopodobnie pójdzie do śmietnika
 */
@Singleton
@Deprecated
public class StamperPdfGenerator {

    @Inject
    private FontsManager fontsManager;

    private DecimalFormat df = new DecimalFormat("0");

    public byte[] generate(Formularz form) {
        try {
            PdfReader pdfReader = new PdfReader(this.getClass().getResource("/templates/prezydent_2015.pdf"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper pdfStamper = new PdfStamper(pdfReader, out);

            Field[] fields = Formularz.class.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Annotation a = f.getAnnotation(FormElement.class);
                if (a != null) {
                    String text = (String) f.get(form);
                    int page = ((FormElement) a).page();
                    int x = ((FormElement) a).x();
                    int y = ((FormElement) a).y();
                    int digits = ((FormElement) a).digits();
                    int cellWidth = ((FormElement) a).cellWidth();

                    if (digits > 0) {
                        text = formatString(text, digits);
                        for (char ch : text.toCharArray()) {
                            print(pdfStamper, "" + ch, page, x, y);
                            x = x + cellWidth;
                        }
                    } else {
                        print(pdfStamper, text, page, x, y);
                    }
                }
            }

            for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
                Image barcode = Image.getInstance(this.getClass().getResource("/barcode.jpeg"));
                barcode.setAbsolutePosition(45f, 10f);
                PdfContentByte content = pdfStamper.getUnderContent(i);
                content.addImage(barcode);
            }

            pdfStamper.close();
            out.close();
            return out.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to stam the form: " + ex.getMessage(), ex);
        }

    }

    private void print(PdfStamper stamper, String text, int page, int x, int y) throws Exception {
        PdfContentByte content = stamper.getUnderContent(page);
        File fontsFolder = fontsManager.getFontsFolder();
        String timesFontPath = fontsFolder.getAbsolutePath() + File.separatorChar + "times.ttf";
        BaseFont bf = BaseFont.createFont(timesFontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 10);
        f.setColor(0, 0, 0);
        Phrase p = new Phrase(text, f);
        ColumnText.showTextAligned(content, Element.ALIGN_LEFT, p, x, y, 0);
    }

    private String formatString(String numberStr, int digits) {
        if (numberStr == null) {
            return "";
        }
        if (numberStr.length() == 0) {
            return "";
        }

        long number = 0;
        try {
            number = Long.parseLong(numberStr);
        } catch (Exception ex) {
            return "Błąd: " + numberStr;
        }

        String formattedNumber = df.format(number);

        while (formattedNumber.length() < digits) {
            formattedNumber = "*" + formattedNumber;
        }

        return formattedNumber;
    }
}