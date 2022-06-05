import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
            Start rozpocznij = new Start();

            Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
            MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");

            System.out.println( mr[0].getRodzaj(0));
            System.out.println( mr[0].getNazwa());
            System.out.println( mr[0].getModel(0));
            System.out.println( mr[0].getWartoscModelu(0));

    }
}