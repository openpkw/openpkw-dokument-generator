package pl.openpkw.poc.backend.rest.pdfservice;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class When_checking_service_availability {

    @Test
    public void should_return_OK() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/backend/rest/pdf");
        String response = target.request().get(String.class);

        assertThat(response, equalTo("OK"));
    }
}