package pl.openpkw.dokument.generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;

import pl.openpkw.dokument.generator.webservice.dto.Form;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Singleton
public class QRCodeGenerator {
    
    @Inject
    private VelocityEngine velocity;
    
    public String createJsonDataForQRCode(Form formData) {
        try {
            VelocityContext context = new VelocityContext();
            context.put("form", formData);
            String json = velocity.process("/templates/QrCodeJsonTemplate.json", context);
            return json;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create json data from Velocity template: " + ex.getMessage(), ex);
        }
    }
    
    public byte[] generate(String input) {
        int size = 125;
        String fileType = "jpeg";
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(input, BarcodeFormat.QR_CODE, size, size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, fileType, out);
            out.close();
            byte[] result = out.toByteArray();
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to generate QR code: " + ex.getMessage());
        }
    }

}