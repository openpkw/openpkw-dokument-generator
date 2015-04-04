package pl.openpkw.poc.backend.webservice.dto;

public class CzlonekKomisji {

    private String imionaINazwisko;

    private String funkcjaWKomisji;

    public String getImionaINazwisko() {
        return imionaINazwisko;
    }

    public void setImionaINazwisko(String imionaINazwisko) {
        this.imionaINazwisko = imionaINazwisko;
    }

    public String getFunkcjaWKomisji() {
        return funkcjaWKomisji;
    }

    public void setFunkcjaWKomisji(String funkcjaWKomisji) {
        this.funkcjaWKomisji = funkcjaWKomisji;
    }

}