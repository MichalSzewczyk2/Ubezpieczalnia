import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        Szyfrowanie szf = new Szyfrowanie();




        Start rozpocznij = new Start("filename.txt","pojazdy.txt");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sd.parse("2000-12-12");
        Klient klient = new Klient(00276252614,"Jan","Kowalski",date,Klient.rodzajeKierowcow.valueOf("GLOWNY_KIEROWCA"),Klient.rodzajeStanowCywilnych.valueOf("ZONATY_ZAMEZNA"),Klient.rodzajePlci.valueOf("MEZCZYZNA"),
                true, 2018, 30100, 4,2,0);

        PlikOdczytZapis plik = new PlikOdczytZapis();
        plik.zapiszPLikTekstowo("klient2.txt",klient);
        Klient nowyklient  =  rozpocznij.wczytajKlient("klient.txt");
        System.out.println("Nowy");
        System.out.println(nowyklient);

        File inFile = new File("klient2.txt");
        Scanner czytaj = new Scanner(inFile);


        String s = "";
        while (czytaj.hasNext()){
            s = czytaj.next();
        }
        s= new Szyfrowanie().cezarDeszyfruj(s);
        System.out.println(s);


        Pojazd auto = new Pojazd( 2018, "BMW", "seria 3", "diesel", 3.0, 150000, 0,"KR 626291");
        rozpocznij.setPojazd(auto);

        MyFrame frame = new MyFrame(rozpocznij);

    }
}
