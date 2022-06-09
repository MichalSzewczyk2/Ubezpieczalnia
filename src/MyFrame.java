import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyFrame extends JFrame implements ActionListener{




















    private void widokStart(MarkaPojazdu[] marki, Pojazd pojazd){
        JButton wprowadzDaneP = new JButton("Wprowadz dane");
        wprowadzDaneP.setBounds(175, 180,150,50);
        wprowadzDaneP.addActionListener(e -> wprowadzKlient(marki, pojazd));
        JButton wczytajDaneP = new JButton("Wczytaj dane");
        wczytajDaneP.setBounds(175,255,150,50);
        wczytajDaneP.addActionListener(e -> wczytajDaneKlienta());
        add(wprowadzDaneP);
        add(wczytajDaneP);
    }

    private void wczytajDaneKlienta(){
        JTextField nazwaPliku = new JTextField();
        JButton dodajNazwe = new JButton("Dodaj");
        dodajNazwe.addActionListener(e -> {
            try {
                policz(nazwaPliku.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        dodajNazwe.setBounds(280, 315, 80,30);
        nazwaPliku.setBounds(175, 310,100, 40);
        nazwaPliku.setVisible(true);
        nazwaPliku.setPreferredSize(new Dimension(100,40));
        System.out.println("Dzilam");
        add(nazwaPliku);
        add(dodajNazwe);
    }

    public void wprowadzKlient(MarkaPojazdu[] marki, Pojazd pojazd){

        removeAll();

        String[] s = new String[marki.length];
        for(int i = 0; i < marki.length; i++){
            s[i] = marki[i].getNazwa();
        }
        JComboBox listaMarek = new JComboBox(s);
        listaMarek.setBounds(50, 20, 80, 40);

        listaMarek.setEditable(true);
        add(listaMarek);

        JComboBox listaModeli = new JComboBox();
        listaModeli.setBounds(50,80, 80,40);
        setModelList(pojazd, marki[0].getNazwa(), marki, listaModeli);
        add(listaModeli);

        JButton przycisk = new JButton();
        przycisk.setBounds(50,140,80,40);
        przycisk.setText("Drukuj");

        add(przycisk);

        przycisk.addActionListener(e -> System.out.println(pojazd.getMarka()));
        listaMarek.addActionListener(e -> this.setModelList(pojazd,((String) listaMarek.getSelectedItem()),marki, listaModeli));
    }


    public MyFrame(MarkaPojazdu[] marki, Pojazd pojazd){
        Container frame = this;
        //generujListeMarek(marki, pojazd);
        //generujListeModeli(marki, pojazd);
        //generujPrzycisk(pojazd);

        setTitle("Ubezpieczenia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1066,600);
        setResizable(false);
        setVisible(true);

        widokStart(marki,pojazd);
        setVisible(true);

    }

    public void policz(String plikKlienta) throws ParseException {
        Start rozpocznij = new Start();



        Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
        MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");
        Klient klient = rozpocznij.wczytajKlient(plikKlienta);
        Pojazd auto = new Pojazd(new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-03"), "BMW", "seria 3", "diesel", 3.0, 150000, 0);


        System.out.println(ub[0].getPrzeliczniki().liczOC(auto,mr,klient));
        System.out.println(ub[0].getPrzeliczniki().liczAC(auto, mr, klient,false));

        JTextField wynik = new JTextField(ub[0].getPrzeliczniki().liczOC(auto,mr,klient)+"");
        wynik.setBounds(175,390, 100,20);
        wynik.setEditable(false);
        add(wynik);
    }

    public void setModelList(Pojazd pojazd, String marka, MarkaPojazdu[] marki, JComboBox listaModeli){
        pojazd.setMarka(marka);
        var n = 0;
        for (int i = 0; i < marki.length; i++) {
            if(marki[i].getNazwa().equals(marka)){
                n=i;
            }
        }
        String[] s = marki[n].getNazwyModeli();
        listaModeli.removeAllItems();
        for(String items: s){
            listaModeli.addItem(items);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
