public class Ubezpieczyciel {

    private String nazwa;
    private String adres;
    private String kontakt;
    private double[] przeliczniki;

    public Ubezpieczyciel(String nazwa, String adres, String kontakt) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.kontakt = kontakt;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }
}
