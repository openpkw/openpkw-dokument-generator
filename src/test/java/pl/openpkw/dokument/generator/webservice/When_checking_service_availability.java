package pl.openpkw.dokument.generator.webservice;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class When_checking_service_availability extends WebServiceTestBase {

    private String environment = TEST;

    @Test
    public void should_return_OK() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + environment + "/openpkw-dokument-generator/service/protocol");
        String response = target.request().get(String.class);

        assertThat(response, equalTo("OK"));
    }
}