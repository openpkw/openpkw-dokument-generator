package pl.openpkw.poc.backend.webservice.dto;

public class AkcjaWyborcza {

    private String dataGlosowania;

    private String dzienMiesiacGlosowania;

    private String rokGlosowania;

    private String godzinaGlosowaniaOd;

    private String godzinaGlosowaniaDo;

    public String getDataGlosowania() {
        return dataGlosowania;
    }

    public void setDataGlosowania(String dataGlosowania) {
        this.dataGlosowania = dataGlosowania;
    }

    public String getDzienMiesiacGlosowania() {
        return dzienMiesiacGlosowania;
    }

    public void setDzienMiesiacGlosowania(String dzienMiesiacGlosowania) {
        this.dzienMiesiacGlosowania = dzienMiesiacGlosowania;
    }

    public String getRokGlosowania() {
        return rokGlosowania;
    }

    public void setRokGlosowania(String rokGlosowania) {
        this.rokGlosowania = rokGlosowania;
    }

    public String getGodzinaGlosowaniaOd() {
        return godzinaGlosowaniaOd;
    }

    public void setGodzinaGlosowaniaOd(String godzinaGlosowaniaOd) {
        this.godzinaGlosowaniaOd = godzinaGlosowaniaOd;
    }

    public String getGodzinaGlosowaniaDo() {
        return godzinaGlosowaniaDo;
    }

    public void setGodzinaGlosowaniaDo(String godzinaGlosowaniaDo) {
        this.godzinaGlosowaniaDo = godzinaGlosowaniaDo;
    }

}
