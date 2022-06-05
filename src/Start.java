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

}
