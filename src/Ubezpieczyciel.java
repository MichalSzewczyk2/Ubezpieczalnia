public class Ubezpieczyciel {

    private String nazwa;
    private String adres;
    private String kontakt;
    private Przeliczniki przeliczniki;

    public Przeliczniki getPrzeliczniki() {
        return przeliczniki;
    }

    public Ubezpieczyciel(String nazwa, String adres, String kontakt, double[] p0, double[] p1, double[] p2, double[] p3) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.kontakt = kontakt;
        this.przeliczniki = new Przeliczniki(p0, p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Ubezpieczyciel{" +
                "nazwa='" + nazwa + '\'' +
                ", adres='" + adres + '\'' +
                ", kontakt='" + kontakt + '\'' +
                ", przeliczniki=" + przeliczniki +
                '}';
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
