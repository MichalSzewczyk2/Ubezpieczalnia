import java.util.Arrays;

public class Przeliczniki {

    private int maxWiekAC;
    private double[] przeliczniki;//0-przelicznik dzieci, 1-przelicznik wieku, 2-przelicznik stan cywilny, 3-przelicznik przebieg, 4- przelicznik wartosci, 5-przelicznik ac
    private double[] przelicznikiRodzaji;//0-osobowka, 1-motor, 2-ciezarowka, 4-autobus, 5-ciagnik rolniczy
    private double[] przelicznikiWojewodztw;
    private double[] przelicznikiSposobUzytkowania;// 0-na ulicy, 1-we wspolnym garazu, 2-teren posesji, 3-w indywidualnym garazu, 4-na parkingu strzezonym, 5-inne miejsce niestrzezone

    public Przeliczniki(int maxWiekAC, double[] przeliczniki, double[] przelicznikiRodzaji, double[] przelicznikiWojewodztw, double[] przelicznikiSposobUzytkowania){
        this.maxWiekAC = maxWiekAC;
        this.przeliczniki = new double[6];
        System.arraycopy(przeliczniki, 0, this.przeliczniki, 0, 6);
        this.przelicznikiRodzaji = new double[5];
        System.arraycopy(przelicznikiRodzaji, 0, this.przelicznikiRodzaji,0, 5);
        this.przelicznikiWojewodztw = new double[16];
        System.arraycopy(przelicznikiWojewodztw, 0, this.przelicznikiWojewodztw, 0, 16);
        this.przelicznikiSposobUzytkowania = new double[6];
        System.arraycopy(przelicznikiSposobUzytkowania, 0, this.przelicznikiSposobUzytkowania, 0, 6);
    }

    @Override
    public String toString() {
        return "Przeliczniki{" +
                "przeliczniki=" + Arrays.toString(przeliczniki) +
                ", przelicznikiRodzaji=" + Arrays.toString(przelicznikiRodzaji) +
                ", przelicznikiWojewodztw=" + Arrays.toString(przelicznikiWojewodztw) +
                ", przelicznikiSposobUzytkowania=" + Arrays.toString(przelicznikiSposobUzytkowania) +
                '}';
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
        return "brak";
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
            case "brak":
                return  0.0;
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


    public int wynikZWieku (Klient klient){
        int wiek = klient.getWiek();
        if(wiek<25)return 250;
        else if(25 < wiek && wiek < 30)return 200;
        else if(30 < wiek && wiek < 35)return 150;
        else if(35 < wiek && wiek < 40)return 100;
        else if(40 < wiek && wiek < 45)return 75;
        else return 50;
    }

    public int wynikZCzasuPrawaJazdy (Klient klient){

        int czas = klient.getCzasPrawaJazdy();

        if( czas < 20 ){
            return 200 - czas * 10 * (-1);
        }
        else return 20;
    }

    public int wynikZCzasuOC (Klient klient){
        int czas = klient.czasPolisyOC;
        if (czas < 10){
            return 1000 - czas *100 * (-1);
        }
        else  return 50;
    }

    public double liczOC(Pojazd auto, MarkaPojazdu[] marki, Klient klient){

        if(auto.getStopien_uszkodzen() == 100){
            System.out.println("Nie można ubezpieczyć pojazdu");
            return 0.0;
        }

        double ubezpieczenie = 0.0;
        ubezpieczenie += auto.getPojemnosc_silnika() * 100;
        ubezpieczenie += auto.getWiekPojazdu() * 10;
        ubezpieczenie += przelicznikiWojewodztw[klient.nrWojewodztwa];
        if(klient.czyDzieci) ubezpieczenie +=  przeliczniki[0];
        ubezpieczenie += wynikZWieku(klient) * przeliczniki[1];
        if(klient.stanCywil == 2) ubezpieczenie += przeliczniki[2];
        ubezpieczenie += wynikZCzasuPrawaJazdy(klient);
        ubezpieczenie += przelicznikiSposobUzytkowania[klient.sposobUzytkowania];
        ubezpieczenie += wynikZCzasuOC(klient);
        ubezpieczenie += (auto.getPrzebieg() / 10000.0)* przeliczniki[3];
        ubezpieczenie = ubezpieczenie * przelicznikZRodzaju(rodzajZModelu(auto, marki));

        return ubezpieczenie;
    }

    public double liczAC(Pojazd auto, MarkaPojazdu[] marki, Klient klient, boolean samoAC){

        if(auto.getWiekPojazdu() > maxWiekAC){
            return 0.0;
        }
        int wartoscPojazdu = wartoscZModelu(auto, marki);

        double ac = wartoscPojazdu * 0.01 * przeliczniki[4];
        ac += przelicznikiSposobUzytkowania[klient.sposobUzytkowania];
        if(samoAC)ac = ac * 1.2;
        ac = ac * przeliczniki[5];
        return ac;
    }

}
