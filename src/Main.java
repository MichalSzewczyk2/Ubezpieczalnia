import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws ParseException {
        Start rozpocznij = new Start("filename.txt","pojazdy.txt");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sd.parse("2000-12-12");
        Klient klient = new Klient(00276252614,"Jan","Kowalski",date,Klient.rodzajeKierowcow.valueOf("GLOWNY_KIEROWCA"),Klient.rodzajeStanowCywilnych.valueOf("ZONATY_ZAMEZNA"),Klient.rodzajePlci.valueOf("MEZCZYZNA"),
                true, 2018, 30100, 4,2,0);

        PlikOdczytZapis plik = new PlikOdczytZapis();
        plik.zapiszPlik("klient2.txt",klient);


        ///Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
        //MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");
        // Klient klient = rozpocznij.wczytajKlient("klient.txt");
        Pojazd auto = new Pojazd( 2018, "BMW", "seria 3", "diesel", 3.0, 150000, 0,"KR 626291");
        rozpocznij.setPojazd(auto);

        MyFrame frame = new MyFrame(rozpocznij);

    }
}
