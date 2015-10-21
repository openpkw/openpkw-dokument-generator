package pl.openpkw.dokument.generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Singleton
public class QRCodeGenerator {

    @Inject
    private VelocityEngine velocity;

    public String createJsonDataForQRCode(Map formData) {
        try {
            VelocityContext context = new VelocityContext();
            context.put("form", formData);
            String json = velocity.process("/templates/QRCodeJsonTemplate.json", context);
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
            int crunchifyWidth = byteMatrix.getWidth();
            int scale = 4;

            BufferedImage image = new BufferedImage(crunchifyWidth * scale, crunchifyWidth * scale, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, crunchifyWidth * scale, crunchifyWidth * scale);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < crunchifyWidth; i++) {
                for (int j = 0; j < crunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i * scale, j * scale, scale, scale);
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