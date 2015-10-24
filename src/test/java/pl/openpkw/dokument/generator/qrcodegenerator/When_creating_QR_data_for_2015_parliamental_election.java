package pl.openpkw.dokument.generator.qrcodegenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import pl.openpkw.dokument.generator.QRCodeGenerator;
import pl.openpkw.dokument.generator.VelocityEngine;

public class When_creating_QR_data_for_2015_parliamental_election {

    @Test
    public void hopsa() throws Exception {
        VelocityEngine velocity = new VelocityEngine();
        velocity.initialize();
        QRCodeGenerator generator = new QRCodeGenerator(velocity);

        String json = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/sample-input-2015-parliament-sejm-periphery.json").toURI())));
        Map<String, Object> jsonData = new ObjectMapper().readValue(json, HashMap.class);
        String result = generator.createQRDataForQRCode("/templates/2015_parliament_periphery.txt", (Map<Object, Object>) jsonData.get("formData"));
        System.out.println(result);
    }
}