import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Start {

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
                Ubezpieczyciel ub = new Ubezpieczyciel(nazwa, adres, kontakt, maxWiekAC, p0, p1, p2, p3);
                ubezpieczyciele[i] = ub;
            }
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

            return marki;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public Klient wczytajKlient (String plik){
        try{
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
            if(Integer.parseInt(czytaj.nextLine()) ==1 ){
                k.setCzyDzieci(true);
            }else k.setCzyDzieci(false);
            k.setDataOtrzymaniaPrawaJazdy(new SimpleDateFormat("yyyy-MM-dd").parse(czytaj.nextLine()));
            k.setKodPocztowy(Integer.parseInt(czytaj.nextLine()));
            k.setCzasPolisyOC(Integer.parseInt(czytaj.nextLine()));
            k.setNrWojewodztwa(Integer.parseInt(czytaj.nextLine()));
            k.setStanCywil(Integer.parseInt(czytaj.nextLine()));
            k.setSposobUzytkowania(Integer.parseInt(czytaj.nextLine()));

            return k;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
