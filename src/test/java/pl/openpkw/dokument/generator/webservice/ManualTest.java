package pl.openpkw.dokument.generator.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ManualTest {
    
    public final static String LOCAL = "localhost:8080";
    public final static String DEV = "dobromir.openpkw.pl:9080";

    public static void main(String[] args) {
        new ManualTest().run();
    }

    public void run() {
        try {
            Client client = ClientBuilder.newClient();
            String env = DEV;
            WebTarget target = client.target("http://"+env+"/openpkw-dokument-generator/service/protocol");

            String form = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/form.json").toURI())));
            System.out.println(form);

            byte[] response = target.request().post(Entity.entity(form, MediaType.APPLICATION_JSON), byte[].class);
            
            System.out.println("Received "+response.length+" bytes.");
            
            File outputFile = new File("/temp/output.pdf");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            outputFileStream.write(response);
            outputFileStream.close();
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}