package pl.openpkw.poc.backend.webservice.dto;

public class CzlonekKomisji {

    private String imie1;

    private String imie2;

    private String nazwisko;

    private String funkcja;

    private boolean obecnosc;

    public String getImie1() {
        return imie1;
    }

    public void setImie1(String imie1) {
        this.imie1 = imie1;
    }

    public String getImie2() {
        return imie2;
    }

    public void setImie2(String imie2) {
        this.imie2 = imie2;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getFunkcja() {
        return funkcja;
    }

    public void setFunkcja(String funkcja) {
        this.funkcja = funkcja;
    }

    public boolean isObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(boolean obecnosc) {
        this.obecnosc = obecnosc;
    }
}