package pl.openpkw.poc.backend.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class WynikiGlosowania {
    private String pole9;
    private String pole9a;
    private String pole10;
    private String pole11;
    private String pole12;
    private String pole13;
    private List<WynikiKandydata> pole14 = new ArrayList<WynikiKandydata>();
    private String sumaGlosowWaznych;

    public String getPole9() {
        return pole9;
    }

    public void setPole9(String pole9) {
        this.pole9 = pole9;
    }

    public String getPole9a() {
        return pole9a;
    }

    public void setPole9a(String pole9a) {
        this.pole9a = pole9a;
    }

    public String getPole10() {
        return pole10;
    }

    public void setPole10(String pole10) {
        this.pole10 = pole10;
    }

    public String getPole11() {
        return pole11;
    }

    public void setPole11(String pole11) {
        this.pole11 = pole11;
    }

    public String getPole12() {
        return pole12;
    }

    public void setPole12(String pole12) {
        this.pole12 = pole12;
    }

    public String getPole13() {
        return pole13;
    }

    public void setPole13(String pole13) {
        this.pole13 = pole13;
    }

    public List<WynikiKandydata> getPole14() {
        return pole14;
    }

    public void setPole14(List<WynikiKandydata> pole14) {
        this.pole14 = pole14;
    }

    public String getSumaGlosowWaznych() {
        return sumaGlosowWaznych;
    }

    public void setSumaGlosowWaznych(String sumaGlosowWaznych) {
        this.sumaGlosowWaznych = sumaGlosowWaznych;
    }
}