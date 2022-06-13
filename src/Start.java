import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Start {

    private PlikOdczytZapis odczytZapis;

    private Ubezpieczyciel[] ubezpieczyciel;
    private MarkaPojazdu[] marki;
    private Klient klient;
    private Pojazd pojazd;
    private Ubezpieczenie ubezpieczenie;

    public Start(String ubplik, String mrplik){
        odczytZapis = new PlikOdczytZapis();
        ubezpieczenie = new Ubezpieczenie();
        wczytajUbezpieczycieli(ubplik);
        wczytajMarki(mrplik);
    }

    public PlikOdczytZapis getOdczytZapis() {
        return odczytZapis;
    }

    public void setOdczytZapis(PlikOdczytZapis odczytZapis) {
        this.odczytZapis = odczytZapis;
    }

    public Ubezpieczyciel[] getUbezpieczyciel() {
        return ubezpieczyciel;
    }

    public void setUbezpieczyciel(Ubezpieczyciel[] ubezpieczyciel) {
        this.ubezpieczyciel = ubezpieczyciel;
    }

    public MarkaPojazdu[] getMarki() {
        return marki;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public void setMarki(MarkaPojazdu[] marki) {
        this.marki = marki;
    }

    public Ubezpieczenie getUbezpieczenie() {
        return ubezpieczenie;
    }

    public void setUbezpieczenie(Ubezpieczenie ubezpieczenie) {
        this.ubezpieczenie = ubezpieczenie;
    }

    public Ubezpieczyciel[] wczytajUbezpieczycieli(String plik){
        try{
            File inFile = new File(plik);
            Scanner czytaj = new Scanner(inFile);
            int liczbaubezpieczycieli = Integer.parseInt(czytaj.nextLine());
            Ubezpieczyciel[] ubezpieczyciele = new Ubezpieczyciel[liczbaubezpieczycieli];

            for (int i = 0; i < liczbaubezpieczycieli; i++) {
                String nazwa = czytaj.nextLine();
                String adres = czytaj.nextLine();
                String kontakt = czytaj.nextLine();
                int maxWiekAC = Integer.parseInt(czytaj.nextLine());
                int nnw = Integer.parseInt(czytaj.nextLine());
                int ass = Integer.parseInt(czytaj.nextLine());
                double[] p0 = new double[6];
                double[] p1 = new double[5];
                double[] p2 = new double[16];
                double[] p3 = new double[6];
                for (int j = 0; j < 6; j++) {
                    p0[j] = Double.parseDouble(czytaj.nextLine());
                }
                for (int j = 0; j < 5; j++) {
                    p1[j] = Double.parseDouble(czytaj.nextLine());
                }
                for (int j = 0; j < 16; j++) {
                    p2[j] = Double.parseDouble(czytaj.nextLine());
                }
                for (int j = 0; j < 6; j++) {
                    p3[j] = Double.parseDouble(czytaj.nextLine());
                }
                Ubezpieczyciel ub = new Ubezpieczyciel(nazwa, adres, kontakt, maxWiekAC, nnw, ass, p0, p1, p2, p3);
                ubezpieczyciele[i] = ub;
            }
            this.ubezpieczyciel = ubezpieczyciele;
            return ubezpieczyciele;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
    public MarkaPojazdu[] wczytajMarki(String plik){
        try{
            File inFile = new File(plik);
            Scanner czytaj = new Scanner(inFile);

            int iloscMarek = Integer.parseInt(czytaj.nextLine());
            MarkaPojazdu[] marki = new MarkaPojazdu[iloscMarek];

            for(int i = 0; i < iloscMarek; i++){

                String marka = czytaj.nextLine();
                int ileModeli = Integer.parseInt(czytaj.nextLine());
                String[] rodzaje = new String[ileModeli];
                String[] modele = new String[ileModeli];
                int[] wartosci = new int[ileModeli];

                for (int n = 0; n < ileModeli; n++){
                    rodzaje[n] = czytaj.nextLine();
                    modele[n] = czytaj.nextLine();
                    wartosci[n] = Integer.parseInt(czytaj.nextLine());
                }
                marki[i] = new MarkaPojazdu(rodzaje, marka, ileModeli, modele, wartosci);
            }
            this.marki = marki;
            return marki;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public Klient wczytajKlient (String plik) {
        try {
            File inFile = new File(plik);
            Scanner czytaj = new Scanner(inFile);

            Klient k = new Klient();

            k.setPesel(Long.parseLong(czytaj.nextLine()));
            k.setImie(czytaj.nextLine());
            k.setNazwisko(czytaj.nextLine());
            k.setDataUrodzenia(new SimpleDateFormat("yyyy-MM-dd").parse(czytaj.nextLine()));
            k.setCzyKierowca(Klient.rodzajeKierowcow.valueOf(czytaj.nextLine()));
            k.setStanCywilny(Klient.rodzajeStanowCywilnych.valueOf(czytaj.nextLine()));
            k.setPlec(Klient.rodzajePlci.valueOf(czytaj.nextLine()));
            if (Integer.parseInt(czytaj.nextLine()) == 1) {
                k.setCzyDzieci(true);
            } else k.setCzyDzieci(false);
            k.setRokOtrzymaniaPrawaJazdy(Integer.parseInt(czytaj.nextLine()));
            k.setKodPocztowy(Integer.parseInt(czytaj.nextLine()));
            k.setCzasPolisyOC(Integer.parseInt(czytaj.nextLine()));
            k.setNrWojewodztwa(Integer.parseInt(czytaj.nextLine()));
            k.setStanCywil(Integer.parseInt(czytaj.nextLine()));
            k.setSposobUzytkowania(Integer.parseInt(czytaj.nextLine()));

            this.klient = k;

            return k;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Klient wczytajKlientCearem (String plik){


            try{

                File inFile = new File(plik);
                Scanner czytaj = new Scanner(inFile);

                String s = "";
                while (czytaj.hasNext()){
                    s = czytaj.next();
                }
                s= new Szyfrowanie().cezarDeszyfruj(s);
                System.out.println(s);
                czytaj = new Scanner(s);

                Klient k = new Klient();

                k.setPesel(Long.parseLong(czytaj.nextLine()));
                k.setImie(czytaj.nextLine());
                k.setNazwisko(czytaj.nextLine());
                k.setDataUrodzenia(new SimpleDateFormat("yyyy-MM-dd").parse(czytaj.nextLine()));
                k.setCzyKierowca(Klient.rodzajeKierowcow.valueOf(czytaj.nextLine()));
                k.setStanCywilny(Klient.rodzajeStanowCywilnych.valueOf(czytaj.nextLine()));
                k.setPlec(Klient.rodzajePlci.valueOf(czytaj.nextLine()));
                if(Integer.parseInt(czytaj.nextLine()) ==1 ){
                    k.setCzyDzieci(true);
                }else k.setCzyDzieci(false);
                k.setRokOtrzymaniaPrawaJazdy(Integer.parseInt(czytaj.nextLine()));
                k.setKodPocztowy(Integer.parseInt(czytaj.nextLine()));
                k.setCzasPolisyOC(Integer.parseInt(czytaj.nextLine()));
                k.setNrWojewodztwa(Integer.parseInt(czytaj.nextLine()));
                k.setStanCywil(Integer.parseInt(czytaj.nextLine()));
                k.setSposobUzytkowania(Integer.parseInt(czytaj.nextLine()));

                this.klient = k;

                return k;

            }catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }catch (ParseException e) {
                e.printStackTrace();
                return null;
            }


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Start start = (Start) o;
        return Arrays.equals(ubezpieczyciel, start.ubezpieczyciel) && Arrays.equals(marki, start.marki) && Objects.equals(klient, start.klient) && Objects.equals(pojazd, start.pojazd) && Objects.equals(ubezpieczenie, start.ubezpieczenie);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(klient, pojazd, ubezpieczenie);
        result = 31 * result + Arrays.hashCode(ubezpieczyciel);
        result = 31 * result + Arrays.hashCode(marki);
        return result;
    }
}
