import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws ParseException {
            Start rozpocznij = new Start();



            Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
            MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");
            Klient klient = rozpocznij.wczytajKlient("klient.txt");
            Pojazd auto = new Pojazd(new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-03"), "BMW", "seria 3", "diesel", 3.0, 150000, 0);


            System.out.println(ub[0].getPrzeliczniki().liczOC(auto,mr,klient));


            System.out.println( ub[0]);
            System.out.println( mr[0]);
            System.out.println( klient);
            System.out.println( auto);
            Date now = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy");
            int rok = Integer.parseInt(sd.format(now));
            System.out.println(rok);

            Pojazd pojazd = new Pojazd();
            MyFrame frame = new MyFrame(mr, pojazd);

    }
}
