package pl.openpkw.poc.webapp;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.openpkw.poc.webapp.domain.Formularz;
import pl.openpkw.poc.webapp.pdf.HtmlPdfGenerator;
import pl.openpkw.poc.webapp.pdf.StamperPdfGenerator;

public class PdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private HtmlPdfGenerator htmlPdfGenerator;

    @Inject
    private StamperPdfGenerator stamperPdfGenerator;

    @Inject
    private FormDataMapper formDataMapper;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Formularz form = formDataMapper.getFormData(request);

        String method = request.getParameter("pdfMethod");
        byte[] pdfFile = null;

        if (method.equals("1")) {
            pdfFile = htmlPdfGenerator.generate(form);
        } else {
            pdfFile = stamperPdfGenerator.generate(form);
        }

        response.getOutputStream().write(pdfFile);
    }
}