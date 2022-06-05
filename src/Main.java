import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
            Start rozpocznij = new Start();

            Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
            MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");

            System.out.println( mr[1].getRodzaj(0));
            System.out.println( mr[1].getNazwa());
            System.out.println( mr[1].getModel(0));
            System.out.println( mr[1].getWartoscModelu(0));
            Date now = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy");
            int rok = Integer.parseInt(sd.format(now));
            System.out.println(rok);

            Pojazd pojazd = new Pojazd();
            MyFrame frame = new MyFrame(mr, pojazd);

    }
}
