import java.text.SimpleDateFormat;
import java.util.Date;

public class Pojazd {


    private Date rok_produkcji;
    private String marka;
    private String model;
    private String rodzaj_paliwa;
    private double pojemnosc_silnika;
    private int przebieg;
    private int stopien_uszkodzen;

    public Pojazd() {

        this.marka = "";
        this.model = "";
        this.rodzaj_paliwa = " ";
        this.pojemnosc_silnika = 0.0;
        this.przebieg = 0;
        this.stopien_uszkodzen = 0;
    }

    public Pojazd(Date rok_produkcji, String marka, String model, String rodzaj_paliwa, double pojemnosc_silnika, int przebieg, int stopien_uszkodzen) {

        this.rok_produkcji = rok_produkcji;
        this.marka = marka;
        this.model = model;
        this.rodzaj_paliwa = rodzaj_paliwa;
        this.pojemnosc_silnika = pojemnosc_silnika;
        this.przebieg = przebieg;
        this.stopien_uszkodzen = stopien_uszkodzen;
    }

    public int getWiekPojazdu(){
        Date now = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        int rok = Integer.parseInt(sd.format(now));
        int rokUrodzenia = Integer.parseInt(sd.format(rok_produkcji));
        return rok - rokUrodzenia;
    }

    public Date getRok_produkcji() {
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

    public void setRok_produkcji(Date rok_produkcji) {
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
}
