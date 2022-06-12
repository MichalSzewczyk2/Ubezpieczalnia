import java.util.Objects;

public class Ubezpieczyciel {

    private String nazwa;
    private String adres;
    private String kontakt;
    private Przeliczniki przeliczniki;
    private int nnw;
    private int ass;


    public Przeliczniki getPrzeliczniki() {
        return przeliczniki;
    }

    public Ubezpieczyciel(String nazwa, String adres, String kontakt, int maxWiekAC, int nnw, int ass, double[] p0, double[] p1, double[] p2, double[] p3) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.kontakt = kontakt;
        this.nnw = nnw;
        this.ass = ass;
        this.przeliczniki = new Przeliczniki(maxWiekAC, p0, p1, p2, p3);
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

    public void setPrzeliczniki(Przeliczniki przeliczniki) {
        this.przeliczniki = przeliczniki;
    }

    public int getNNW() {
        return nnw;
    }

    public void setNNW(int nnw) {
        this.nnw = nnw;
    }

    public int getAss() {
        return ass;
    }

    public void setAss(int ass) {
        this.ass = ass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubezpieczyciel that = (Ubezpieczyciel) o;
        return nnw == that.nnw && ass == that.ass && Objects.equals(nazwa, that.nazwa) && Objects.equals(adres, that.adres) && Objects.equals(kontakt, that.kontakt) && Objects.equals(przeliczniki, that.przeliczniki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, adres, kontakt, przeliczniki, nnw, ass);
    }
}
