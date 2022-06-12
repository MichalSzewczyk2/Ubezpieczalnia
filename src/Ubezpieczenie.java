import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ubezpieczenie {
    String nazwaUbezpieczyciela;
    String rodzaj;
    String cena;
    Date dataRozpoczecia;
    Date dataZakonczenia;
    String peselKlienta;          //jako id wlasciciela
    String nrRejestracyjny ;    //jako id ubezpieczonego samochodu



    public Ubezpieczenie() {
    }

    public Ubezpieczenie(String txt) {
        fromString(txt);
    }

    public Ubezpieczenie(String nazwa, String rodzaj, String cena, Date dataRozpoczecia,
                         Date dataZakonczenia, String peselKlienta, String nrRejestracyjny) {
        this.nazwaUbezpieczyciela = nazwa;
        this.rodzaj = rodzaj;
        this.cena = cena;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.peselKlienta = peselKlienta;
        this.nrRejestracyjny = nrRejestracyjny;
    }



    public void fromString(String txt)
    {
        String[] dane = txt.trim().split("\t");
        if(dane.length != 7)
            throw new IllegalArgumentException("Argument funkcji jest niepoprawny");

        try {
            nazwaUbezpieczyciela = dane[0];
            rodzaj = dane[1];
            cena = dane[2];
            dataRozpoczecia = new SimpleDateFormat("yyyy-MM-dd").parse(dane[3]);
            dataZakonczenia = new SimpleDateFormat("yyyy-MM-dd").parse(dane[4]);
            peselKlienta = dane[5];
            nrRejestracyjny = dane[6];
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public String toString()
    {
        return nazwaUbezpieczyciela + "\t" +
                rodzaj + "\t" +
                cena + "\t" +
                new SimpleDateFormat("yyyy-MM-dd").format(dataRozpoczecia) + "\t" +
                new SimpleDateFormat("yyyy-MM-dd").format(dataZakonczenia) + "\t"+
                peselKlienta + "\t" +
                nrRejestracyjny;
    }



    public String getNazwa() {
        return nazwaUbezpieczyciela;
    }

    public void setNazwa(String nazwa) {
        this.nazwaUbezpieczyciela = nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public String getPeselKlienta() {
        return peselKlienta;
    }

    public void setPeselKlienta(String peselKlienta) {
        this.peselKlienta = peselKlienta;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

}
