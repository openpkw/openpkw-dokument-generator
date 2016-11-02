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
import javax.ws.rs.core.Response;

public class ManualTest {
    
    public final static String LOCAL = "localhost:8080";
    public final static String TEST = "rumcajs.openpkw.pl:9080";
    
    public final static String SAMPLE_INPUT_2015_PRESIDENT_PERIPHERY = "/sample-input-2015-president-periphery.json";
    public final static String SAMPLE_INPUT_2015_PARLIAMENT_SEJM_PERIPHERY = "/sample-input-2015-parliament-sejm-periphery.json";

    public static void main(String[] args) {
        new ManualTest().run(TEST, SAMPLE_INPUT_2015_PARLIAMENT_SEJM_PERIPHERY);
    }

    public void run(String env, String input) {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://"+env+"/openpkw-dokument-generator/service/protocol");

            String form = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(input).toURI())));

            Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_JSON));
            System.out.println("Status: "+response.getStatus());
            byte[] responseContent = (byte[]) response.readEntity(byte[].class);

            if (response.getStatus() != 200) {
                System.out.println(new String(responseContent));
                return;
            }

            System.out.println("Received "+responseContent.length+" bytes.");
            File outputFile = new File("/temp/output.pdf");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            outputFileStream.write(responseContent);
            outputFileStream.close();
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}