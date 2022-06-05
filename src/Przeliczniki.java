public class Przeliczniki {

    private double[] rodzajeP;//0-baza, 1-rokProdukcji, 2-rodzajPaliwa, 3-pojemnoscSilnika, 4-przebieg, 5-uszkodzenia
    private double[] przelicznikiModeli;

    public Przeliczniki(double[] przeliczniki){
        this.rodzajeP = new double[14];
        System.arraycopy(przeliczniki, 0, rodzajeP, 0, 14);
    }

    public double dopasujRodzaj (String rodzaj){

        for(int i = 0; i < RodzajPojazdu.getIloscRodzajow() ; i++){
            if(RodzajPojazdu.getRodzaj()[i].equals(rodzaj))return rodzajeP[i];
        }

        return -1.0;
    }

    public double liczUbezpieczenie(Pojazd auto){
        double ubezpieczenie = rodzajeP[0];//baza

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



        return ubezpieczenie;
    }





}
