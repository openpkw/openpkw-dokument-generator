package pl.openpkw.poc.backend.rest.dto;

import java.util.List;

public class Form {

    private Komisja komisja;
    private AkcjaWyborcza akcjaWyborcza;
    private RozliczenieKart rozliczenieKart;
    private WynikiGlosowania wynikiGlosowania;
    private UwagiIAdnotacje uwagiIAdnotacje;
    private List<CzlonekKomisji> czlonkowieKomisji;

    public Komisja getKomisja() {
        return komisja;
    }

    public void setKomisja(Komisja komisja) {
        this.komisja = komisja;
    }

    public AkcjaWyborcza getAkcjaWyborcza() {
        return akcjaWyborcza;
    }

    public void setAkcjaWyborcza(AkcjaWyborcza akcjaWyborcza) {
        this.akcjaWyborcza = akcjaWyborcza;
    }

    public RozliczenieKart getRozliczenieKart() {
        return rozliczenieKart;
    }

    public void setRozliczenieKart(RozliczenieKart rozliczenieKart) {
        this.rozliczenieKart = rozliczenieKart;
    }

    public WynikiGlosowania getWynikiGlosowania() {
        return wynikiGlosowania;
    }

    public void setWynikiGlosowania(WynikiGlosowania wynikiGlosowania) {
        this.wynikiGlosowania = wynikiGlosowania;
    }

    public UwagiIAdnotacje getUwagiIAdnotacje() {
        return uwagiIAdnotacje;
    }

    public void setUwagiIAdnotacje(UwagiIAdnotacje uwagiIAdnotacje) {
        this.uwagiIAdnotacje = uwagiIAdnotacje;
    }

    public List<CzlonekKomisji> getCzlonkowieKomisji() {
        return czlonkowieKomisji;
    }

    public void setCzlonkowieKomisji(List<CzlonekKomisji> czlonkowieKomisji) {
        this.czlonkowieKomisji = czlonkowieKomisji;
    }
}