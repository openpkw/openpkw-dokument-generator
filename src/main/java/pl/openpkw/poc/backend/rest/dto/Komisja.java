package pl.openpkw.poc.backend.rest.dto;

public class Komisja {

    private String kodTerytorialnyGminy;

    private String numerObwoduGlosowania;

    private String siedzibaObwodowejKomisjiWyborczej;

    private String gminaDzielnica;

    private String powiat;

    private String wojewodztwo;

    public String getKodTerytorialnyGminy() {
        return kodTerytorialnyGminy;
    }

    public void setKodTerytorialnyGminy(String kodTerytorialnyGminy) {
        this.kodTerytorialnyGminy = kodTerytorialnyGminy;
    }

    public String getNumerObwoduGlosowania() {
        return numerObwoduGlosowania;
    }

    public void setNumerObwoduGlosowania(String numerObwoduGlosowania) {
        this.numerObwoduGlosowania = numerObwoduGlosowania;
    }

    public String getSiedzibaObwodowejKomisjiWyborczej() {
        return siedzibaObwodowejKomisjiWyborczej;
    }

    public void setSiedzibaObwodowejKomisjiWyborczej(String siedzibaObwodowejKomisjiWyborczej) {
        this.siedzibaObwodowejKomisjiWyborczej = siedzibaObwodowejKomisjiWyborczej;
    }

    public String getGminaDzielnica() {
        return gminaDzielnica;
    }

    public void setGminaDzielnica(String gminaDzielnica) {
        this.gminaDzielnica = gminaDzielnica;
    }

    public String getPowiat() {
        return powiat;
    }

    public void setPowiat(String powiat) {
        this.powiat = powiat;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

}