import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;


public class PlikOdczytZapis {
    /*
       public ArrayList<?> odczytajPlikTekstowo(String nazwaPliku)
       {
           ArrayList<?> lista = new ArrayList<>();
           try {
               File plik = new File(nazwaPliku);
               Scanner czytaj = new Scanner(plik);
               while(czytaj.hasNextLine())
               {
                   ArrayList<?> obiekt = new klient();
                   obiekt.fromString(czytaj.nextLine());
                   //lub (odkomentowac):
                   //klient obiekt = new klient(czytaj.nextLine());
                   lista.add(obiekt);
               }
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           } catch (IllegalArgumentException e)
           {
               throw new RuntimeException(e);
           }
           return lista;
       }
   */
    public void zapiszPLikTekstowo(String nazwaPliku, ArrayList<?> lista)
    {
        try {
            PrintWriter zapis = new PrintWriter(nazwaPliku);

            for(int i = 0; i < lista.size(); i++)
                zapis.println(lista.get(i));
            zapis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void zapiszPLikTekstowo(String nazwaPliku, Object objekt)
    {
        try{
            PrintWriter zapis = new PrintWriter(nazwaPliku);
            zapis.println(objekt);
            zapis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void zapiszPLikTekstowo(String nazwaPliku, String string)
    {
        try{
            PrintWriter zapis = new PrintWriter(nazwaPliku);
            zapis.println(string);
            zapis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<Klient> odczytajKlienta(String nazwaPliku)
    {
        ArrayList<Klient> lista = new ArrayList<Klient>();
        try {
            File plik = new File(nazwaPliku);
            Scanner czytaj = new Scanner(plik);
            while(czytaj.hasNextLine())
            {
                Klient obiekt = new Klient();
                obiekt.fromString(czytaj.nextLine());
                //lub (odkomentowac):
                //klient obiekt = new klient(czytaj.nextLine());
                lista.add(obiekt);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e)
        {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public ArrayList<Ubezpieczenie> odczytajUbezpieczenia(String nazwaPliku)
    {
        ArrayList<Ubezpieczenie> lista = new ArrayList<Ubezpieczenie>();
        try {
            File plik = new File(nazwaPliku);
            Scanner czytaj = new Scanner(plik);
            while(czytaj.hasNextLine())
            {
                Ubezpieczenie obiekt = new Ubezpieczenie();
                obiekt.fromString(czytaj.nextLine());
                //lub (odkomentowac):
                //klient obiekt = new klient(czytaj.nextLine());
                lista.add(obiekt);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e)
        {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void zapiszKlienta(String nazwaPliku, ArrayList<Klient> lista)
    {
        try {
            PrintWriter zapis = new PrintWriter(nazwaPliku);
            for(int i = 0; i < lista.size(); i++)
                zapis.println(lista.get(i));
            zapis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void zapiszUbezpieczenia(String nazwaPliku, ArrayList<Ubezpieczenie> lista)
    {
        try {
            PrintWriter zapis = new PrintWriter(nazwaPliku);
            for(int i = 0; i < lista.size(); i++)
                zapis.println(lista.get(i));
            zapis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public Object odczytajPlik(String nazwaPliku) {
        Object obj = null;
        try {
            ObjectInputStream plik = new ObjectInputStream(new FileInputStream(nazwaPliku));
            obj = plik.readObject();        //odczytanie obiektu
            plik.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public void zapiszPlik(String nazwaPliku, Object objekt)
    {
        try {
            ObjectOutputStream plik = new ObjectOutputStream(new FileOutputStream(nazwaPliku));
            plik.writeObject(objekt);      // zapisanie obiektu
            plik.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
