public class Pojazd {

    private int rodzajP;
    private int rok_produkcji;
    private String marka;
    private String model;
    private String rodzaj_paliwa;
    private double pojemnosc_silnika;
    private int przebieg;
    private int stopien_uszkodzen;

    public Pojazd() {
        this.rodzajP = 0;
        this.rok_produkcji = 0;
        this.marka = "";
        this.model = "";
        this.rodzaj_paliwa = " ";
        this.pojemnosc_silnika = 0.0;
        this.przebieg = 0;
        this.stopien_uszkodzen = 0;
    }

    public Pojazd(int rodzajP, int rok_produkcji, String marka, String model, String rodzaj_paliwa, double pojemnosc_silnika, int przebieg, int stopien_uszkodzen) {
        this.rodzajP = rodzajP;
        this.rok_produkcji = rok_produkcji;
        this.marka = marka;
        this.model = model;
        this.rodzaj_paliwa = rodzaj_paliwa;
        this.pojemnosc_silnika = pojemnosc_silnika;
        this.przebieg = przebieg;
        this.stopien_uszkodzen = stopien_uszkodzen;
    }

    public int getRodzajP() {
        return rodzajP;
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
}
