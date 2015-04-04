package pl.openpkw.poc.backend.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.openpkw.poc.backend.pdf.HtmlPdfGenerator;
import pl.openpkw.poc.backend.rest.dto.Form;

@Path("pdf")
public class PdfService {

    @Inject
    private HtmlPdfGenerator pdfGenerator;

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