import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Pojazd {


    private int rok_produkcji;
    private String marka;
    private String model;
    private String rodzaj_paliwa;
    private double pojemnosc_silnika;
    private int przebieg;
    private int stopien_uszkodzen;
    private String nrRejestracyjny;

    public Pojazd() {

        this.marka = "";
        this.model = "";
        this.rodzaj_paliwa = "";
        this.pojemnosc_silnika = 0.0;
        this.przebieg = 0;
        this.stopien_uszkodzen = 0;
        this.nrRejestracyjny = "";
    }

    public Pojazd(int rok_produkcji, String marka, String model, String rodzaj_paliwa, double pojemnosc_silnika, int przebieg, int stopien_uszkodzen, String nrRejestracyjny) {

        this.rok_produkcji = rok_produkcji;
        this.marka = marka;
        this.model = model;
        this.rodzaj_paliwa = rodzaj_paliwa;
        this.pojemnosc_silnika = pojemnosc_silnika;
        this.przebieg = przebieg;
        this.stopien_uszkodzen = stopien_uszkodzen;
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public int getWiekPojazdu(){
        Date now = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        int rok = Integer.parseInt(sd.format(now));
        return rok - this.rok_produkcji;
    }

    public int getRok_produkcji() {
        return rok_produkcji;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public String getRodzaj_paliwa() {
        return rodzaj_paliwa;
    }


    public double getPojemnosc_silnika() {
        return pojemnosc_silnika;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public int getStopien_uszkodzen() {
        return stopien_uszkodzen;
    }

    public void setRok_produkcji(int rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRodzaj_paliwa(String rodzaj_paliwa) {
        this.rodzaj_paliwa = rodzaj_paliwa;
    }

    public void setPojemnosc_silnika(double pojemnosc_silnika) {
        this.pojemnosc_silnika = pojemnosc_silnika;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public void setStopien_uszkodzen(int stopien_uszkodzen) {
        this.stopien_uszkodzen = stopien_uszkodzen;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    @Override
    public String toString() {
        return "Pojazd{" +
                "rok_produkcji=" + rok_produkcji +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rodzaj_paliwa='" + rodzaj_paliwa + '\'' +
                ", pojemnosc_silnika=" + pojemnosc_silnika +
                ", przebieg=" + przebieg +
                ", stopien_uszkodzen=" + stopien_uszkodzen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojazd pojazd = (Pojazd) o;
        return rok_produkcji == pojazd.rok_produkcji && Double.compare(pojazd.pojemnosc_silnika, pojemnosc_silnika) == 0 && przebieg == pojazd.przebieg && stopien_uszkodzen == pojazd.stopien_uszkodzen && Objects.equals(marka, pojazd.marka) && Objects.equals(model, pojazd.model) && Objects.equals(rodzaj_paliwa, pojazd.rodzaj_paliwa) && Objects.equals(nrRejestracyjny, pojazd.nrRejestracyjny);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rok_produkcji, marka, model, rodzaj_paliwa, pojemnosc_silnika, przebieg, stopien_uszkodzen, nrRejestracyjny);
    }
}
