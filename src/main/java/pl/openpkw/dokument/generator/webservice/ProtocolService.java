package pl.openpkw.dokument.generator.webservice;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.openpkw.dokument.generator.DokumentGeneratorException;
import pl.openpkw.dokument.generator.HtmlPdfGenerator;

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
        return "1.1.0";
    }

    @GET
    @Produces("text/plain")
    public String test() {
        return "OK";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response generatePDF(Map<Object, Object> form) {
        try {
            byte[] pdfDocument = pdfGenerator.generate(form);
            return Response.ok(pdfDocument).build();
        } catch (DokumentGeneratorException ex) {
            String json = "{";
            json += "\"ErrorCode\":\""+ex.getError().getErrorCode()+"\"";
            json += ",\"ErrorMessage\":\""+ex.getError().getErrorMessage()+"\"";
            if (ex.getAdditionalInfo() != null) {
                json += ",\"AdditionalInfo\":\""+ex.getAdditionalInfo()+"\"";
            }
            json += "}";
            return Response.status(400).entity(json).type(MediaType.APPLICATION_JSON).build();
        }
    }
}