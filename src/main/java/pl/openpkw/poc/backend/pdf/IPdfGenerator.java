package pl.openpkw.poc.backend.pdf;

import pl.openpkw.poc.backend.domain.Formularz;

public interface IPdfGenerator {

    public byte[] generate(Formularz form);

}
