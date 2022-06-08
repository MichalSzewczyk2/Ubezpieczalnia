import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyFrame extends JFrame implements ActionListener{
    JButton przycisk;
    JComboBox listaMarek;
    JComboBox listaModeli;
    JButton wprowadzDaneP;
    JButton wczytajDaneP;

    private void generujListeMarek(MarkaPojazdu[] marki, Pojazd pojazd){
        String[] s = new String[marki.length];
        for(int i = 0; i < marki.length; i++){
            s[i] = marki[i].getNazwa();
        }
        listaMarek = new JComboBox(s);
        listaMarek.addActionListener(e -> this.setModelList(pojazd,((String) listaMarek.getSelectedItem()),marki));
        listaMarek.setEditable(true);
        this.add(listaMarek);
    }
    public void generujListeModeli(MarkaPojazdu[] marki, Pojazd pojazd){
        listaModeli = new JComboBox();
        setModelList(pojazd, marki[0].getNazwa(), marki);
        this.add(listaModeli);
    }
    private void generujPrzycisk(Pojazd pojazd){
        przycisk = new JButton();
        przycisk.setBounds(0,0,100,100);
        przycisk.setText("Drukuj");
        przycisk.addActionListener(e -> System.out.println(pojazd.getMarka()));
        this.add(przycisk);
    }

    private void widokStart(){
        wprowadzDaneP = new JButton("Wprowadz dane");
        wprowadzDaneP.setBounds(175, 180,150,50);
        //wprowadzDaneP.addActionListener();
        wczytajDaneP = new JButton("Wczytaj dane");
        wczytajDaneP.setBounds(175,255,150,50);
        wczytajDaneP.addActionListener(this);
        this.add(wprowadzDaneP);
        this.add(wczytajDaneP);
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
        this.add(nazwaPliku);
        this.add(dodajNazwe);
    }

    public MyFrame(MarkaPojazdu[] marki, Pojazd pojazd){

        //generujListeMarek(marki, pojazd);
        //generujListeModeli(marki, pojazd);
        //generujPrzycisk(pojazd);

        this.setTitle("Ubezpieczenia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setResizable(false);
        this.setVisible(true);

        widokStart();


    }

    public void policz(String plikKlienta) throws ParseException {
        Start rozpocznij = new Start();



        Ubezpieczyciel[] ub = rozpocznij.wczytajUbezpieczycieli("filename.txt");
        MarkaPojazdu[] mr = rozpocznij.wczytajMarki("pojazdy.txt");
        Klient klient = rozpocznij.wczytajKlient(plikKlienta);
        Pojazd auto = new Pojazd(new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-03"), "BMW", "seria 3", "diesel", 3.0, 150000, 0);


        System.out.println(ub[0].getPrzeliczniki().liczOC(auto,mr,klient));
        System.out.println(ub[0].getPrzeliczniki().liczAC(auto, mr, klient,false));
    }

    public void setModelList(Pojazd pojazd, String marka, MarkaPojazdu[] marki){
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
        if(e.getSource()==listaMarek){

        }
        if(e.getSource()==przycisk){

        }
        if(e.getSource()==wczytajDaneP) {
            wczytajDaneKlienta();
        }
    }
}
