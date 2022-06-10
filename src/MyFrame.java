import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame implements ActionListener{

    private Start start;
    private JTextField potwierdzenie = new JTextField();


    private void widokStart(){
        getContentPane().removeAll();

        JButton wprowadzDaneP = new JButton("Wprowadz dane");
        wprowadzDaneP.setBounds(373, 180,150,50);

        wprowadzDaneP.addActionListener(e -> wprowadzKlient());

        JButton wczytajDaneP = new JButton("Wczytaj dane");
        wczytajDaneP.setBounds(543,180,150,50);
        wczytajDaneP.addActionListener(e -> wczytajDaneKlienta());
        add(wprowadzDaneP);
        add(wczytajDaneP);

        revalidate();
        repaint();


    }

    private void sprawdzWczytanieKlienta(String plik){


        potwierdzenie.setBounds(638,185,200 ,30);
        potwierdzenie.setBackground(new Color(255,255,255));
        potwierdzenie.setBorder(new LineBorder(Color.white));
        potwierdzenie.setEditable(false);
        if (plik.equals("")){
            potwierdzenie.setText("Nie udało się wczytać klienta!");
            System.out.println("Nie wczytano klienta 1");
        }
        start.wczytajKlient(plik);
        if(start.getKlient() != null && start.getKlient().czyKompletny()){
            potwierdzenie.setText("Udało się wczytać klienta!");
            System.out.println("Wczytano klienta");
        }else {
            potwierdzenie.setText("Nie udało się wczytać klienta!");
            System.out.println("Nie wczytano klienta 2");
        }
        add(potwierdzenie);
        repaint();

    }

    private void wczytajDaneKlienta(){
        getContentPane().removeAll();

        JButton powrot = new JButton("Wróć");
        powrot.setBounds(1000,0,66,50);
        powrot.setBackground(new Color(255,0,0));
        powrot.addActionListener(e -> widokStart());
        add(powrot);

        JTextField nazwaPliku = new JTextField();
        JButton dodajNazwe = new JButton("Dodaj");

        JButton liczOC = new JButton("Oblicz OC");
        JButton liczAC = new JButton("Oblicz AC");
        liczOC.setBounds(373, 300,150,50);
        liczAC.setBounds(543,300,150,50);
        liczOC.addActionListener(e -> policz(true));
        liczAC.addActionListener(e -> policz(false));

        dodajNazwe.addActionListener(e -> sprawdzWczytanieKlienta(nazwaPliku.getText()));
        nazwaPliku.setBounds(438, 180,100, 40);
        dodajNazwe.setBounds(548, 185, 80,30);
        nazwaPliku.setVisible(true);
        nazwaPliku.setPreferredSize(new Dimension(100,40));
        System.out.println("Dzilam");


        revalidate();
        repaint();
        add(liczOC);
        add(liczAC);
        add(nazwaPliku);
        add(dodajNazwe);
    }

    public void wprowadzKlient(){

        getContentPane().removeAll();

        String[] s = new String[start.getMarki().length];
        for(int i = 0; i < start.getMarki().length; i++){
            s[i] = start.getMarki()[i].getNazwa();
        }
        JLabel marka = new JLabel("Wybierz markę pojazdu");
        marka.setBounds(5,20,150, 40);
        marka.setHorizontalAlignment(JLabel.CENTER);
        add(marka);
        JComboBox listaMarek = new JComboBox(s);
        listaMarek.setBounds(160, 20, 80, 40);
        listaMarek.setEditable(false);
        add(listaMarek);

        JLabel model = new JLabel("Wybierz model pojazdu");
        model.setBounds(5,80,150, 40);
        model.setHorizontalAlignment(JLabel.CENTER);
        add(model);
        JComboBox listaModeli = new JComboBox();
        listaModeli.setBounds(160,80, 80,40);
        setModelList(start.getMarki()[0].getNazwa(), listaModeli);
        add(listaModeli);

        JLabel pojemnosc = new JLabel("Wpisz pojemnosc silnika");
        pojemnosc.setBounds(5,140,150, 40);
        pojemnosc.setHorizontalAlignment(JLabel.CENTER);
        add(pojemnosc);
        JTextField pojemnoscSilnika = new JTextField();
        pojemnoscSilnika.setBounds(160,140,80,40);
        add(pojemnoscSilnika);

        JLabel rok = new JLabel("Wpisz rok produkcji");
        rok.setBounds(5,200,150, 40);
        rok.setHorizontalAlignment(JLabel.CENTER);
        add(rok);
        JTextField rokProdukcji = new JTextField();
        rokProdukcji.setBounds(160,200,80,40);
        add(rokProdukcji);

        JLabel przebieg = new JLabel("Wpisz przebig pojazdu");
        przebieg.setBounds(5,260,150, 40);
        przebieg.setHorizontalAlignment(JLabel.CENTER);
        add(przebieg);
        JTextField przebiegPojazdu = new JTextField();
        przebiegPojazdu.setBounds(160,260,80,40);
        add(przebiegPojazdu);

        JLabel paliwo = new JLabel("Wybierz rodzaj paliwa");
        paliwo.setBounds(5,320,150, 40);
        paliwo.setHorizontalAlignment(JLabel.CENTER);
        add(paliwo);
        String[] paliwa = {"benzyna","diesel"};
        JComboBox rodzajPaliwa = new JComboBox<>(paliwa);
        rodzajPaliwa.setBounds(160,320,80,40);
        rodzajPaliwa.setEditable(false);
        add(rodzajPaliwa);


        JButton upPojazd = new JButton("Dodaj dane");
        upPojazd.setBounds(300,200,100,50);
        upPojazd.addActionListener(e -> aktualizujPojazd(listaMarek,listaModeli,rodzajPaliwa,pojemnoscSilnika,przebiegPojazdu,rokProdukcji));
        add(upPojazd);

        JButton przycisk = new JButton();
        przycisk.setBounds(400,140,80,40);
        przycisk.setText("Drukuj");


        JButton powrot = new JButton("Wróć");
        powrot.setBounds(1000,0,66,50);
        powrot.setBackground(new Color(255,0,0));
        powrot.addActionListener(e -> widokStart());
        add(powrot);


        add(przycisk);

        przycisk.addActionListener(e -> System.out.println(start.getPojazd()));
        listaMarek.addActionListener(e -> this.setModelList(((String) listaMarek.getSelectedItem()), listaModeli));


        revalidate();
        repaint();
    }


    public MyFrame(Start start){
        this.start = start;
        JLabel tytul = new JLabel("Porównywarka ubezpieczeń");
        tytul.setBounds(283,10,500,30);
        tytul.setFont(new Font("Arial",Font.BOLD, 30));
        tytul.setHorizontalAlignment(JLabel.CENTER);
        //generujListeMarek(marki, pojazd);
        //generujListeModeli(marki, pojazd);
        //generujPrzycisk(pojazd);

        setTitle("Ubezpieczenia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1066,600);
        setResizable(false);
        setVisible(true);

        add(tytul);
        widokStart();
        setVisible(true);

    }

    public void policz(boolean ac_oc) {


        JTextField wynik = new JTextField();

        if(ac_oc){
            wynik.setText((int)start.getUbezpieczyciel()[0].getPrzeliczniki().liczOC(start.getPojazd(),start.getMarki(),start.getKlient())+"");
        }else wynik.setText((int)start.getUbezpieczyciel()[0].getPrzeliczniki().liczAC(start.getPojazd(),start.getMarki(),start.getKlient(), false)+"");

        wynik.setBounds(483,390, 100,20);
        wynik.setEditable(false);
        wynik.setHorizontalAlignment(JTextField.CENTER);
        add(wynik);
    }

    public void aktualizujPojazd(JComboBox listaMarek, JComboBox listaModeli, JComboBox rodzajP, JTextField pojemnoscSilnika, JTextField przebiegPojazdu, JTextField rokProdukcjiPojazdu){

        String marka = (String)listaMarek.getSelectedItem();
        String model = (String)listaModeli.getSelectedItem();
        String rodzajPaliwa = (String)rodzajP.getSelectedItem();
        double pojemnosc = Double.parseDouble(pojemnoscSilnika.getText());
        int przebieg = Integer.parseInt(przebiegPojazdu.getText());
        int rokProdukcji = Integer.parseInt(rokProdukcjiPojazdu.getText());

        start.getPojazd().setMarka(marka);
        start.getPojazd().setModel(model);
        start.getPojazd().setRodzaj_paliwa(rodzajPaliwa);
        start.getPojazd().setPojemnosc_silnika(pojemnosc);
        start.getPojazd().setPrzebieg(przebieg);
        start.getPojazd().setRok_produkcji(rokProdukcji);

    }

    public void setModelList(String marka, JComboBox listaModeli){
        start.getPojazd().setMarka(marka);
        var n = 0;
        for (int i = 0; i < start.getMarki().length; i++) {
            if(start.getMarki()[i].getNazwa().equals(marka)){
                n=i;
            }
        }
        String[] s = start.getMarki()[n].getNazwyModeli();
        listaModeli.removeAllItems();
        for(String items: s){
            listaModeli.addItem(items);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
