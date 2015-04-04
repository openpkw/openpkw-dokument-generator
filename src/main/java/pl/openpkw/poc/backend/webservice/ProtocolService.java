package pl.openpkw.poc.backend.webservice;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.openpkw.poc.backend.pdf.HtmlPdfGenerator;
import pl.openpkw.poc.backend.webservice.dto.Form;

/**
 * Represents Protokol kalkulatora wyborczego. 
 * 
 * 
 */
@Path("/protocol")
public class ProtocolService {

    @Inject
    private HtmlPdfGenerator pdfGenerator;

    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion() {
        return "1.0.0-SNAPSHOT ToDo";
    }

    @GET
    @Produces("text/plain")
    public String test() {
        return "OK";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response generatePDF(Form form) {
        byte[] pdfDocument = pdfGenerator.generate(form);
        return Response.ok(pdfDocument).build();
    }
}
