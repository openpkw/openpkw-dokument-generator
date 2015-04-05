package pl.openpkw.poc.backend.rest.pdfservice;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

public class When_requesting_PDF_document {

    @Test
    public void should_return_binary_file_with_PDF_signature() throws Exception {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/backend/service/protocol");

        String form = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("form.json").toURI())));
        System.out.println(form);

        byte[] response = target.request().post(Entity.entity(form, MediaType.APPLICATION_JSON), byte[].class);

        assertThat(response, is(notNullValue()));
        assertThat(response.length, is(greaterThan(0)));
        assertThat(new String(response).substring(0, 4), equalTo("%PDF"));
    }
}