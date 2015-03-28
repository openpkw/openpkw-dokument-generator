package pl.openpkw.poc.webapp.pdf;

import pl.openpkw.poc.webapp.domain.Formularz;

public interface IPdfGenerator {

    public byte[] generate(Formularz form);

}
