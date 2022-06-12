import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws ParseException {
            Start rozpocznij = new Start("filename.txt","pojazdy.txt");


            ///Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
            //MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");
            //Klient klient = rozpocznij.wczytajKlient("klient.txt");
            Pojazd auto = new Pojazd( 2018, "BMW", "seria 3", "diesel", 3.0, 150000, 0,"KR 626291");
            rozpocznij.setPojazd(auto);

            MyFrame frame = new MyFrame(rozpocznij);

    }
}
