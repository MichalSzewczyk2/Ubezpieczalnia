import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Start {

    public Ubezpieczyciel[] wczytajUbezpieczycieli(String filename){
        try{
            File inFile = new File(filename);
            Scanner czytaj = new Scanner(inFile);
            int liczbaubezpieczycieli = Integer.parseInt(czytaj.nextLine());
            Ubezpieczyciel[] ubezpieczyciele = new Ubezpieczyciel[liczbaubezpieczycieli];


            int i = 0;

            while (czytaj.hasNextLine()){
                String nazwa = czytaj.nextLine();
                String adres = czytaj.nextLine();
                String kontakt = czytaj.nextLine();

                Ubezpieczyciel ub = new Ubezpieczyciel(nazwa, adres, kontakt);
                ubezpieczyciele[i] = ub;
                i++;
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

}
