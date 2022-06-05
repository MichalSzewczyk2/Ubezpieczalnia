import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarkaPojazdu extends RodzajPojazdu{

    private String nazwa;
    private int iloscModeli;
    private String[] nazwyModeli;
    private int[] wartoscModeli;

    public MarkaPojazdu(String nazwa, int iloscModeli, String[] nazwyModeli, int[] wartoscModeli) {
        this.nazwa = nazwa;
        this.iloscModeli = iloscModeli;
        this.nazwyModeli = nazwyModeli;
        this.wartoscModeli = wartoscModeli;
    }
}
