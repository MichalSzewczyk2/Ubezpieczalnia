import java.util.Arrays;

public class MarkaPojazdu{

    private String[] rodzaje;
    private String nazwa;
    private int iloscModeli;
    private String[] nazwyModeli;
    private int[] wartoscModeli;

    public MarkaPojazdu(String[] rodzaje, String nazwa, int iloscModeli, String[] nazwyModeli, int[] wartoscModeli) {
        this.rodzaje = rodzaje;
        this.nazwa = nazwa;
        this.iloscModeli = iloscModeli;
        this.nazwyModeli = nazwyModeli;
        this.wartoscModeli = wartoscModeli;
    }

    @Override
    public String toString() {
        return "MarkaPojazdu{" +
                "rodzaje=" + Arrays.toString(rodzaje) +
                ", nazwa='" + nazwa + '\'' +
                ", iloscModeli=" + iloscModeli +
                ", nazwyModeli=" + Arrays.toString(nazwyModeli) +
                ", wartoscModeli=" + Arrays.toString(wartoscModeli) +
                '}';
    }

    public String[] getRodzaje() {
        return rodzaje;
    }

    public String getRodzaj(int i) {
        return rodzaje[i];
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getIloscModeli() {
        return iloscModeli;
    }

    public String[] getNazwyModeli() {
        return nazwyModeli;
    }

    public String getModel(int i){
        return this.nazwyModeli[i];
    }

    public int[] getWartoscModeli() {
        return wartoscModeli;
    }

    public int getWartoscModelu(int i){
        return this.wartoscModeli[i];
    }
}
