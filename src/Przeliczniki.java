import java.text.SimpleDateFormat;
import java.util.Date;

public class Przeliczniki {

    private double[] rodzajeP;//0-baza, 1-rokProdukcji, 2-rodzajPaliwa, 3-pojemnoscSilnika, 4-przebieg, 5-uszkodzenia
    private double[] przelicznikiRodzaji;//0-osobowka, 1-motor, 2-ciezarowka, 4-autobus, 5-ciagnik rolniczy

    public Przeliczniki(double[] przeliczniki, double[] przelicznikiRodzaji){
        this.rodzajeP = new double[przeliczniki.length];
        this.przelicznikiRodzaji = new double[przelicznikiRodzaji.length];
        System.arraycopy(przeliczniki, 0, this.rodzajeP, 0, przeliczniki.length);
        System.arraycopy(przelicznikiRodzaji, 0, this.przelicznikiRodzaji,0,przelicznikiRodzaji.length);
    }

    public String rodzajZModelu (Pojazd auto, MarkaPojazdu[] marki){

        for (MarkaPojazdu markaPojazdu : marki) {
            if (auto.getMarka().equals(markaPojazdu.getNazwa())) {
                for (int n = 0; n < markaPojazdu.getIloscModeli(); n++) {
                    if (auto.getModel().equals(markaPojazdu.getModel(n))) {
                        return markaPojazdu.getRodzaj(n);
                    }
                }
            }
        }
        return null;
    }

    public double przelicznikZRodzaju (String rodzaj){
        switch (rodzaj) {
            case "osobowka":
                return przelicznikiRodzaji[0];
            case "motor":
                return przelicznikiRodzaji[1];
            case "ciezarowka":
                return przelicznikiRodzaji[2];
            case "autobus":
                return przelicznikiRodzaji[3];
            case "ciagnik rolniczy":
                return przelicznikiRodzaji[4];
        }
        return 0.0;
    }

    public int wartoscZModelu(Pojazd auto, MarkaPojazdu[] marki){
        for (MarkaPojazdu markaPojazdu : marki) {
            if (auto.getMarka().equals(markaPojazdu.getNazwa())) {
                for (int n = 0; n < markaPojazdu.getIloscModeli(); n++) {
                    if (auto.getModel().equals(markaPojazdu.getModel(n))) {
                        return markaPojazdu.getWartoscModelu(n);
                    }
                }
            }
        }
        return 0;
    }

   /* public double przelicznikZModelu(Pojazd auto) {
        for (int i = 0; i < nazwyModeli.length; i++){
            if(auto.getModel().equals(nazwyModeli[i])){
                return przelicznikiModeli[i];
            }
        }
        return 0.0;
    }
*/
    public double liczUbezpieczenie(Pojazd auto, MarkaPojazdu[] marki){

        Date now = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        int rok = Integer.parseInt(sd.format(now));
        int orginalnaWartosc = wartoscZModelu(auto, marki);

        double obecnaWartosc = orginalnaWartosc - orginalnaWartosc * 0.02 *orginalnaWartosc/(rok-auto.getRok_produkcji()) ;

        double ubezpieczenie = obecnaWartosc * rodzajeP[0];//baza

        ubezpieczenie += przelicznikZRodzaju(rodzajZModelu(auto, marki)) * obecnaWartosc * 0.0001;//rodzaj auta

        ubezpieczenie += (auto.getRok_produkcji()-1960)*20*rodzajeP[1];//rocznik

        if(auto.getRodzaj_paliwa().equals("benzyna")){//rodzaj paliwa
            ubezpieczenie += 20 * rodzajeP[2];
        }else{
            ubezpieczenie += 20 * rodzajeP[2]/2;
        }

        if(auto.getPojemnosc_silnika()<2.0){//pojemnosc silnika
            ubezpieczenie += 2 * rodzajeP[3];
        }else if(auto.getPojemnosc_silnika() > 2.0 && auto.getPojemnosc_silnika() < 5.0){
            ubezpieczenie += 4 * rodzajeP[3];
        }else{
            ubezpieczenie += 8 * rodzajeP[3];
        }

        ubezpieczenie += auto.getPrzebieg() * -1 * 0.0001 * rodzajeP[4] /2;//przebieg

        ubezpieczenie += auto.getStopien_uszkodzen() * obecnaWartosc * -1 * rodzajeP[5];

        return ubezpieczenie;
    }





}
