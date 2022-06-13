public class Szyfrowanie {

    public String cezarSzyfruj (String tekst){

        String wynik  ="";

        for (int i = 0 ; i < tekst.length(); i++){
            wynik += (char)(tekst.charAt(i) + 13);
        }
        return wynik;
    }

    public String cezarDeszyfruj(String tekst){

        String wynik  ="";

        for (int i = 0 ; i < tekst.length(); i++){
            wynik += (char)(tekst.charAt(i) - 13);
        }
        return wynik;
    }



}
