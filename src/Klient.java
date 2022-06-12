import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Klient {
    public enum rodzajeKierowcow{
        PASAZER,
        GLOWNY_KIEROWCA
    }
    public enum rodzajeStanowCywilnych{
        KAWALER_PANNA,
        ZONATY_ZAMEZNA,
        WDOWIEC_WDOWA,
        ROZWIEDZIONY_ROZWIEDZIONA,
    }
    public enum rodzajePlci{
        KOBIETA,
        MEZCZYZNA
    }

    long pesel;
    String imie;
    String nazwisko;
    Date dataUrodzenia;
    rodzajeKierowcow czyKierowca;
    rodzajeStanowCywilnych stanCywilny;
    rodzajePlci plec;
    boolean czyDzieci;
    int rokOtrzymaniaPrawaJazdy;
    int kodPocztowy;
    int czasPolisyOC;
    int nrWojewodztwa; // 0-15
    int stanCywil;
    int sposobUzytkowania; // 0-na ulicy, 1-we wspolnym garazu, 2-teren posesji, 3-w indywidualnym garazu, 4-na parkingu strzezonym, 5-inne miejsce niestrzezone



    public Klient() {
    }

    public Klient(String txt) {
        fromString(txt);
    }

    public Klient(long pesel, String imie, String nazwisko, Date dataUrodzenia,
                  rodzajeKierowcow czyKierowca, rodzajeStanowCywilnych stanCywilny, rodzajePlci plec,
                  boolean czyDzieci, int rokOtrzymaniaPrawaJazdy, int kodPocztowy, int nrWojewodztwa, int stanCywil, int sposobUzytkowania) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.czyKierowca = czyKierowca;
        this.stanCywilny = stanCywilny;
        this.plec = plec;
        this.czyDzieci = czyDzieci;
        this.rokOtrzymaniaPrawaJazdy = rokOtrzymaniaPrawaJazdy;
        this.kodPocztowy = kodPocztowy;
        this.nrWojewodztwa = nrWojewodztwa;
        this.stanCywil = stanCywil;
        this.sposobUzytkowania = sposobUzytkowania;
    }



    public void fromString(String txt)
    {


        String[] dane = txt.trim().split("\n");
        System.out.println(Arrays.toString(dane));
        if(dane.length != 14)
            throw new IllegalArgumentException("Argument funkcji jest niepoprawny");

        try {
            pesel = Long.parseLong(dane[0]);
            imie = dane[1];
            nazwisko = dane[2];
            dataUrodzenia =  new SimpleDateFormat("yyyy-MM-dd").parse(dane[3]);
            czyKierowca = rodzajeKierowcow.valueOf(dane[4]);
            stanCywilny = rodzajeStanowCywilnych.valueOf(dane[5]);
            plec = rodzajePlci.valueOf(dane[6]);
            if(Integer.parseInt(dane[7]) ==1 ){
                czyDzieci = true;
            }else czyDzieci = false;
            rokOtrzymaniaPrawaJazdy = Integer.parseInt(dane[8]);
            kodPocztowy = Integer.parseInt(dane[9]);
            czasPolisyOC = Integer.parseInt(dane[10]);
            nrWojewodztwa = Integer.parseInt(dane[11]);
            stanCywil = Integer.parseInt(dane[12]);
            sposobUzytkowania = Integer.parseInt(dane[13]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return pesel + "\t" +
                imie + "\t" +
                nazwisko + "\t" +
                dataUrodzenia + "\t" +
                czyKierowca + "\t" +
                stanCywilny + "\t" +
                plec + "\t" +
                czyDzieci + "\t" +
                rokOtrzymaniaPrawaJazdy + "\t" +
                kodPocztowy;
    }

    public boolean czyKompletny(){
        return true;
    }

    public int getWiek(){
        Date now = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        int rok = Integer.parseInt(sd.format(now));
        int rokUrodzenia = Integer.parseInt(sd.format(dataUrodzenia));
        return rok - rokUrodzenia;
    }
    public int getCzasPrawaJazdy(){
        Date now = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        int rok = Integer.parseInt(sd.format(now));
        int rokPrawaJazdy = Integer.parseInt(sd.format(rokOtrzymaniaPrawaJazdy));
        return rok - rokPrawaJazdy;
    }
    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public rodzajeKierowcow getCzyKierowca() {
        return czyKierowca;
    }

    public void setCzyKierowca(rodzajeKierowcow czyKierowca) {
        this.czyKierowca = czyKierowca;
    }

    public rodzajeStanowCywilnych getStanCywilny() {
        return stanCywilny;
    }

    public void setStanCywilny(rodzajeStanowCywilnych stanCywilny) {
        this.stanCywilny = stanCywilny;
    }

    public rodzajePlci getPlec() {
        return plec;
    }

    public void setPlec(rodzajePlci plec) {
        this.plec = plec;
    }

    public int getRokOtrzymaniaPrawaJazdy() {
        return rokOtrzymaniaPrawaJazdy;
    }

    public void setRokOtrzymaniaPrawaJazdy(int rokOtrzymaniaPrawaJazdy) {
        this.rokOtrzymaniaPrawaJazdy = rokOtrzymaniaPrawaJazdy;
    }

    public int getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(int kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public boolean isCzyDzieci() {
        return czyDzieci;
    }

    public void setCzyDzieci(boolean czyDzieci) {
        this.czyDzieci = czyDzieci;
    }

    public int getCzasPolisyOC() {
        return czasPolisyOC;
    }

    public void setCzasPolisyOC(int czasPolisyOC) {
        this.czasPolisyOC = czasPolisyOC;
    }

    public int getNrWojewodztwa() {
        return nrWojewodztwa;
    }

    public void setNrWojewodztwa(int nrWojewodztwa) {
        this.nrWojewodztwa = nrWojewodztwa;
    }

    public int getStanCywil() {
        return stanCywil;
    }

    public void setStanCywil(int stanCywil) {
        this.stanCywil = stanCywil;
    }

    public int getSposobUzytkowania() {
        return sposobUzytkowania;
    }

    public void setSposobUzytkowania(int sposobUzytkowania) {
        this.sposobUzytkowania = sposobUzytkowania;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klient klient = (Klient) o;
        return pesel == klient.pesel && czyDzieci == klient.czyDzieci && rokOtrzymaniaPrawaJazdy == klient.rokOtrzymaniaPrawaJazdy && kodPocztowy == klient.kodPocztowy && czasPolisyOC == klient.czasPolisyOC && nrWojewodztwa == klient.nrWojewodztwa && stanCywil == klient.stanCywil && sposobUzytkowania == klient.sposobUzytkowania && Objects.equals(imie, klient.imie) && Objects.equals(nazwisko, klient.nazwisko) && Objects.equals(dataUrodzenia, klient.dataUrodzenia) && czyKierowca == klient.czyKierowca && stanCywilny == klient.stanCywilny && plec == klient.plec;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, imie, nazwisko, dataUrodzenia, czyKierowca, stanCywilny, plec, czyDzieci, rokOtrzymaniaPrawaJazdy, kodPocztowy, czasPolisyOC, nrWojewodztwa, stanCywil, sposobUzytkowania);
    }
}
