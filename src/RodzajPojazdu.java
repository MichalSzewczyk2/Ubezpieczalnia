import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RodzajPojazdu {

    private static String[] rodzaj;
    private static int iloscRodzajow;

    private static MarkaPojazdu[] marki;
    private static int iloscMarek;

    public RodzajPojazdu() {
        iloscRodzajow = 6;
        rodzaj = new String[6];
        rodzaj[0]= "pusty";
        rodzaj[1]= "osobowka";
        rodzaj[2]= "motocykl";
        rodzaj[3]= "ciagnik_rolniczy";
        rodzaj[4]= "autobus";
        rodzaj[5]= "ciezarowka";


    }
   /* public void wczytajMarki (String plik){
        try{
            File inFile = new File(plik);
            Scanner czytaj = new Scanner(inFile);

            iloscMarek = Integer.parseInt(czytaj.nextLine());
            marki = new MarkaPojazdu[iloscMarek];

            for(int i = 0; i < iloscMarek; i++){
                String rodzaje = czytaj.nextLine();
                String marka = czytaj.nextLine();
                int ileModeli = Integer.parseInt(czytaj.nextLine());
                String[] modele = new String[ileModeli];
                int[] wartosci = new int[ileModeli];

                for (int n = 0; n < ileModeli; n++){
                    modele[n] = czytaj.nextLine();
                    wartosci[n] = Integer.parseInt(czytaj.nextLine());
                }
                marki[i] = new MarkaPojazdu(rodzaje, marka, ileModeli, modele, wartosci);
            }


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    */
    public static int getIloscRodzajow() {
        return iloscRodzajow;
    }
    public static String[] getRodzaj() {
        return rodzaj;
    }
}
