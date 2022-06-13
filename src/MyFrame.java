import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyFrame extends JFrame implements ActionListener{

    private Start start;
    private JTextField potwierdzenie = new JTextField();
    boolean daneKlienta;
    boolean danePojazdu;
    boolean daneUbezpieczenia;
    boolean[] opcjeUbezpieczenia;

    private void widokStart(){
        getContentPane().removeAll();

        JButton wprowadzDaneP = new JButton("Wprowadz dane");
        wprowadzDaneP.setBounds(373, 180,320,50);
        wprowadzDaneP.setHorizontalAlignment(JButton.CENTER);
        wprowadzDaneP.addActionListener(e -> {
            wprowadzPojazd();
            wprowadzKlient();
        });
        add(wprowadzDaneP);

        /*
        JButton wczytajDaneP = new JButton("Wczytaj dane");
        wczytajDaneP.setBounds(543,180,150,50);
        wczytajDaneP.setHorizontalAlignment(JButton.CENTER);
        wczytajDaneP.addActionListener(e -> wczytajDaneKlienta());
        add(wczytajDaneP);
        */
        JButton opcjeU = new JButton("Opcje ubezpieczenia");
        opcjeU.setBounds(373,240,320,50);
        opcjeU.setHorizontalAlignment(JButton.CENTER);
        opcjeU.addActionListener(e -> opcjeUbezpieczenia());
        add(opcjeU);

        JButton porownajUbezpieczenia = new JButton("Porównaj ubezpieczenia");
        porownajUbezpieczenia.setBounds(373, 300,320,50);
        porownajUbezpieczenia.setHorizontalAlignment(JButton.CENTER);
        porownajUbezpieczenia.addActionListener(e -> {
            if(!danePojazdu || !daneKlienta || !daneUbezpieczenia){
                komunikat("Brakuje danych!");
            }else{
                pokazOferty();
            }

        });
        add(porownajUbezpieczenia);


        //Wskaźniki stanu
        JLabel wskk = new JLabel("Dane klienta");
        JLabel wskkkol = new JLabel();
        JLabel wskp = new JLabel("Dane pojazdu");
        JLabel wskpkol = new JLabel();
        JLabel wsku = new JLabel("Dane ubezpieczenia");
        JLabel wskukol = new JLabel();

        wskk.setBounds(10,300,120,40);
        wskkkol.setBounds(130,300,40,40);
        wskp.setBounds(10,350,120,40);
        wskpkol.setBounds(130,350,40,40);
        wsku.setBounds(10,400,120,40);
        wskukol.setBounds(130,400,40,40);

        wskk.setHorizontalAlignment(JLabel.CENTER);
        wskkkol.setHorizontalAlignment(JLabel.CENTER);
        wskp.setHorizontalAlignment(JLabel.CENTER);
        wskpkol.setHorizontalAlignment(JLabel.CENTER);
        wsku.setHorizontalAlignment(JLabel.CENTER);
        wskukol.setHorizontalAlignment(JLabel.CENTER);

        wskk.setBorder(new LineBorder(new Color(0)));
        wskkkol.setBorder(new LineBorder(new Color(0)));
        wskp.setBorder(new LineBorder(new Color(0)));
        wskpkol.setBorder(new LineBorder(new Color(0)));
        wsku.setBorder(new LineBorder(new Color(0)));
        wskukol.setBorder(new LineBorder(new Color(0)));

        if(daneKlienta){
            wskkkol.setText("OK");
        }else wskkkol.setText("Błąd");
        if(danePojazdu){
            wskpkol.setText("OK");
        }else wskpkol.setText("Błąd");
        if(daneUbezpieczenia){
            wskukol.setText("OK");
        }else wskukol.setText("Błąd");

        add(wskk);
        add(wskkkol);
        add(wskp);
        add(wskpkol);
        add(wsku);
        add(wskukol);

        //Tytuł strony
        JLabel tytul = new JLabel("Porównywarka ubezpieczeń pojazdów");
        tytul.setFont(new Font("Arial",Font.BOLD,30));
        tytul.setBounds(50,30,1000,100);
        tytul.setHorizontalAlignment(JLabel.CENTER);
        add(tytul);

        revalidate();
        repaint();


    }

    private void sprawdzWczytanieKlienta(String plik) throws MyException {


        potwierdzenie.setBounds(638,185,200 ,30);
        potwierdzenie.setBackground(new Color(255,255,255));
        potwierdzenie.setBorder(new LineBorder(Color.white));
        potwierdzenie.setEditable(false);
        if (plik.equals("")){
            potwierdzenie.setText("Nie udało się wczytać klienta!");
        }
        start.wczytajKlient(plik);
        if(start.getKlient() != null && start.getKlient().czyKompletny()){
            potwierdzenie.setText("Udało się wczytać klienta!");
            daneKlienta = true;
            System.out.println("Wczytano klienta");
        }else {
            potwierdzenie.setText("Nie udało się wczytać klienta!");
            throw  new MyException("Nie udało się wczytać klienta");
        }
        add(potwierdzenie);
        repaint();

    }

    private void wczytajDaneKlienta(){

        getContentPane().removeAll();

        //Tytuł strony
        JLabel tytul = new JLabel("Wybierz pliki do wczytania");
        tytul.setFont(new Font("Arial",Font.BOLD,30));
        tytul.setBounds(50,30,1000,100);
        tytul.setHorizontalAlignment(JLabel.CENTER);
        add(tytul);


        JButton wybierzPlikKlienta = new JButton("Wybierz plik klienta");
        wybierzPlikKlienta.setBounds(390,250,150,50);
        add(wybierzPlikKlienta);
        wybierzPlikKlienta.addActionListener(e -> {
            JFileChooser wybierak = new JFileChooser();
            wybierak.setCurrentDirectory(new File("."));
            int odpowiedz = wybierak.showOpenDialog(null);

            if(odpowiedz == JFileChooser.APPROVE_OPTION){
                File plik = new File((wybierak.getSelectedFile().getAbsolutePath()));
                daneKlienta = true;
                start.wczytajKlient(plik.getName());
            }
        });



        JTextField nazwaPliku = new JTextField();
        JButton dodajNazwe = new JButton("Dodaj");

        JButton liczOC = new JButton("Oblicz OC");
        JButton liczAC = new JButton("Oblicz AC");
        liczOC.setBounds(373, 300,150,50);
        liczAC.setBounds(543,300,150,50);
        liczOC.addActionListener(e -> policz(true));
        liczAC.addActionListener(e -> policz(false));

        dodajNazwe.addActionListener(e -> {
            try {
                sprawdzWczytanieKlienta(nazwaPliku.getText());
            } catch (MyException ex) {
                ex.printStackTrace();
            }
        });
        nazwaPliku.setBounds(438, 180,100, 40);
        dodajNazwe.setBounds(548, 185, 80,30);
        nazwaPliku.setVisible(true);
        nazwaPliku.setPreferredSize(new Dimension(100,40));

        //Przycisk powrotu
        JButton powrot = new JButton("Wróć");
        powrot.setBounds(985,0,100,50);
        powrot.setBackground(new Color(255,0,0));
        powrot.setHorizontalAlignment(JButton.CENTER);
        powrot.addActionListener(e -> widokStart());
        add(powrot);

        revalidate();
        repaint();
        add(liczOC);
        add(liczAC);
        add(nazwaPliku);
        add(dodajNazwe);

        repaint();
    }

    public void opcjeUbezpieczenia(){

        getContentPane().removeAll();

        //Tytuł strony
        JLabel tytul = new JLabel("Wybierz opcje dotyczące ubezpieczenia");
        tytul.setFont(new Font("Arial",Font.BOLD,30));
        tytul.setBounds(50,30,1000,100);
        tytul.setHorizontalAlignment(JLabel.CENTER);
        add(tytul);

        //Wybór rodzaju ubezpieczenia
        JLabel ocL = new JLabel("OC");
        JLabel acL = new JLabel("AC");
        JLabel nwwL = new JLabel("NNW");
        JLabel asisstanceL = new JLabel("Assistance");
        ocL.setBounds(255,250,140,50);
        acL.setBounds(405,250,140,50);
        nwwL.setBounds(555,250,140,50);
        asisstanceL.setBounds(705,250,140,50);
        ocL.setHorizontalAlignment(JLabel.CENTER);
        acL.setHorizontalAlignment(JLabel.CENTER);
        nwwL.setHorizontalAlignment(JLabel.CENTER);
        asisstanceL.setHorizontalAlignment(JLabel.CENTER);
        ocL.setBorder(new LineBorder(new Color(0)));
        acL.setBorder(new LineBorder(new Color(0)));
        nwwL.setBorder(new LineBorder(new Color(0)));
        asisstanceL.setBorder(new LineBorder(new Color(0)));
        add(ocL);
        add(acL);
        add(nwwL);
        add(asisstanceL);
        JCheckBox ocC = new JCheckBox();
        JCheckBox acC = new JCheckBox();
        JCheckBox nwwC = new JCheckBox();
        JCheckBox asisstanceC = new JCheckBox();
        ocC.setBounds(255,310,140,50);
        acC.setBounds(405,310,140,50);
        nwwC.setBounds(555,310,140,50);
        asisstanceC.setBounds(705,310,140,50);
        ocC.setHorizontalAlignment(JCheckBox.CENTER);
        acC.setHorizontalAlignment(JCheckBox.CENTER);
        nwwC.setHorizontalAlignment(JCheckBox.CENTER);
        asisstanceC.setHorizontalAlignment(JCheckBox.CENTER);
        add(ocC);
        add(acC);
        add(nwwC);
        add(asisstanceC);

        //Przycisk dodawania danych
        JButton dodaj = new JButton("Zapisz");
        dodaj.setBounds(500,400,100,40);
        dodaj.setHorizontalAlignment(JButton.CENTER);
        dodaj.addActionListener(e -> {
                if(ocC.isSelected())opcjeUbezpieczenia[0]=true;
                if(acC.isSelected())opcjeUbezpieczenia[1]=true;
                if(nwwC.isSelected())opcjeUbezpieczenia[2]=true;
                if(asisstanceC.isSelected())opcjeUbezpieczenia[3]=true;
                daneUbezpieczenia = true;
                komunikat("Zapisano dane");
        });
        add(dodaj);

        //Przycisk powrotu
        JButton powrot = new JButton("Wróć");
        powrot.setBounds(985,0,100,50);
        powrot.setBackground(new Color(255,0,0));
        powrot.setHorizontalAlignment(JButton.CENTER);
        powrot.addActionListener(e -> widokStart());
        add(powrot);

        revalidate();
        repaint();

    }

    public void komunikat(String s) {

        getContentPane().removeAll();


        JLabel ogloszenie = new JLabel(s);
        ogloszenie.setBounds(0,0,1100,900);
        ogloszenie.setHorizontalAlignment(JLabel.CENTER);
        ogloszenie.setVerticalAlignment(JLabel.CENTER);
        ogloszenie.setFont(new Font("Arial",Font.BOLD, 40));
        add(ogloszenie);

        revalidate();

        repaint();

        ActionListener akcja = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                widokStart();
            }
        };
        Timer timer = new Timer(1000,akcja);
        timer.setRepeats(false);
        timer.start();
    }

    public void wprowadzKlient(){

        //Pole do wpisania imienia
        JLabel imieL = new JLabel("Imie:");
        imieL.setBounds(600,20,150,40);
        imieL.setHorizontalAlignment(JLabel.CENTER);
        add(imieL);
        JTextField imieJ = new JTextField();
        imieJ.setBounds(760,20,150,40);
        add(imieJ);

        //Pole do wpisania nazwiska
        JLabel nazwiskoL = new JLabel("Nazwisko:");
        nazwiskoL.setBounds(600,80,150,40);
        nazwiskoL.setHorizontalAlignment(JLabel.CENTER);
        add(nazwiskoL);
        JTextField nazwiskoJ = new JTextField();
        nazwiskoJ.setBounds(760,80,150,40);
        add(nazwiskoJ);

        //Pole wyboru płci
        String[] plecS = {"KOBIETA","MEZCZYZNA"};
        JLabel plecL = new JLabel("Płeć:");
        plecL.setBounds(600,140,150,40);
        plecL.setHorizontalAlignment(JLabel.CENTER);
        add(plecL);
        JComboBox<String> plecJ = new JComboBox<>(plecS);
        plecJ.setBounds(760,140,150,40);
        plecJ.setEditable(false);
        add(plecJ);

        //pole wyboru daty urodzenia
        String[] lataS = {"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005"};
        String[] miesiaceS = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] dniS = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JLabel dataUrodzeniaL = new JLabel("Data urodzenia");
        dataUrodzeniaL.setBounds(600,200,150,40);
        dataUrodzeniaL.setHorizontalAlignment(JLabel.CENTER);
        add(dataUrodzeniaL);
        JComboBox dniJ = new JComboBox<>(dniS);
        dniJ.setBounds(760,200,45,40);
        dniJ.setEditable(false);
        add(dniJ);
        JComboBox miesaceJ = new JComboBox<>(miesiaceS);
        miesaceJ.setBounds(810,200,45,40);
        miesaceJ.setEditable(false);
        add(miesaceJ);
        JComboBox lataJ = new JComboBox<>(lataS);
        lataJ.setBounds(860,200,55,40);
        lataJ.setEditable(false);
        add(lataJ);



        //pole wpisania nr pesel
        JLabel peselL = new JLabel("PESEL:");
        peselL.setBounds(600,260,150,40);
        peselL.setHorizontalAlignment(JLabel.CENTER);
        add(peselL);
        JTextField peselJ = new JTextField();
        peselJ.setBounds(760,260,150,40);
        add(peselJ);

        //pole wyboru wojewodztwa
        String[] wojewodztwoS = {"Dolnośląskie","Kujawsko-pomorskie","Lubelskie","Lubuskie","Lódzkie","Małopolskie","Mazowieckie","Opolskie","Podkarpackie","Podlaskie","Pomorskie","Sląskie","Swietokrzyskie","Warmińsko-mazurskie","Wielkopolskie","Zachodniopomorskie"};
        JLabel wojewodztwoL = new JLabel("Wojewodztwo:");
        wojewodztwoL.setBounds(600,320,150,40);
        wojewodztwoL.setHorizontalAlignment(JLabel.CENTER);
        add(wojewodztwoL);
        JComboBox wojewodztwoJ = new JComboBox<>(wojewodztwoS);
        wojewodztwoJ.setBounds(760,320,150,40);
        wojewodztwoJ.setEditable(false);
        add(wojewodztwoJ);
        wojewodztwoJ.addActionListener(e -> System.out.println(wojewodztwoJ.getSelectedIndex()));

        //pole wyboru stanu cywilnego
        String[] stanCywilnyS = {"KAWALER_PANNA","ZONATY_ZAMEZNA","WDOWIEC_WDOWA","ROZWIEDZIONY_ROZWIEDZIONA"};
        JLabel stanCywilnyL = new JLabel("Stan cywilny: ");
        stanCywilnyL.setBounds(600,380,150,40);
        stanCywilnyL.setHorizontalAlignment(JLabel.CENTER);
        add(stanCywilnyL);
        JComboBox stanCywilnyJ = new JComboBox<>(stanCywilnyS);
        stanCywilnyJ.setBounds(760,380,150,40);
        stanCywilnyJ.setEditable(false);
        add(stanCywilnyJ);

        //pole wyboru czy poosiada dzieci
        String[] dzieciS = {"Tak","Nie"};
        JLabel dzieciL = new JLabel("Czy posiada dzieci poniżej 26 lat");
        dzieciL.setBounds(600,440,150,40);
        dzieciL.setHorizontalAlignment(JLabel.CENTER);
        add(dzieciL);
        JComboBox dzieciJ = new JComboBox<>(dzieciS);
        dzieciJ.setBounds(760,440,150,40);
        dzieciJ.setEditable(false);
        add(dzieciJ);

        //pole wpisania kodu pocztowego
        JLabel kodPocztowyL = new JLabel("Kod pocztowy:");
        kodPocztowyL.setBounds(600,500,150,40);
        kodPocztowyL.setHorizontalAlignment(JLabel.CENTER);
        add(kodPocztowyL);
        JLabel kodPocztowyPauzaL = new JLabel("-");
        kodPocztowyPauzaL.setBounds(800,500,10,40);
        kodPocztowyPauzaL.setHorizontalAlignment(JLabel.CENTER);
        add(kodPocztowyPauzaL);
        JTextField kodPocztowy1J = new JTextField("00");
        kodPocztowy1J.setBounds(760,500,40,40);
        add(kodPocztowy1J);
        JTextField kodPocztowy2J = new JTextField("000");
        kodPocztowy2J.setBounds(810,500,100,40);
        add(kodPocztowy2J);


        //pole wyboru daty prawa jazdy
        JLabel prawoJazdyL = new JLabel("Rok otrzymania prawa jazdy");
        prawoJazdyL.setBounds(600,560,150,40);
        prawoJazdyL.setHorizontalAlignment(JLabel.CENTER);
        add(prawoJazdyL);
        JTextField prawoJazdyJ = new JTextField();
        prawoJazdyJ.setBounds(760,560,150,40);
        add(prawoJazdyJ);

        //pole wpisania czasu oc
        JLabel ocL = new JLabel("Ile lat kupuje OC");
        ocL.setBounds(600,620,150,40);
        ocL.setHorizontalAlignment(JLabel.CENTER);
        add(ocL);
        JTextField ocJ = new JTextField();
        ocJ.setBounds(760,620,150,40);
        add(ocJ);

        //pole wyboru sposobu uzytkowania
        // 0-na ulicy, 1-we wspolnym garazu, 2-teren posesji, 3-w indywidualnym garazu, 4-na parkingu strzezonym, 5-inne miejsce niestrzezone
        String[] sposobUzytkowaniaS = {"na ulicy","we wspolnym garazu","teren posesji","w indywidualnym garazu","na parkingu strzezonym","inne miejsce niestrzezone"};
        JLabel sposobUzytkowaniaL = new JLabel("Sposob uzytkowania:");
        sposobUzytkowaniaL.setBounds(600,680,150,40);
        sposobUzytkowaniaL.setHorizontalAlignment(JLabel.CENTER);
        add(sposobUzytkowaniaL);
        JComboBox sposobUzytkowaniaJ = new JComboBox<>(sposobUzytkowaniaS);
        sposobUzytkowaniaJ.setBounds(760,680,150,40);
        sposobUzytkowaniaJ.setEditable(false);
        add(sposobUzytkowaniaJ);


        JButton wybierzPlikKlienta = new JButton("Wybierz plik klienta");
        wybierzPlikKlienta.setBounds(600,800,150,40);
        add(wybierzPlikKlienta);
        wybierzPlikKlienta.addActionListener(e -> {
            JFileChooser wybierak = new JFileChooser();
            wybierak.setCurrentDirectory(new File("."));
            int odpowiedz = wybierak.showOpenDialog(null);

            if(odpowiedz == JFileChooser.APPROVE_OPTION){
                File plik = new File((wybierak.getSelectedFile().getAbsolutePath()));
                daneKlienta = true;
                start.wczytajKlient(plik.getName());
            }
        });

        JButton dodajDane =  new JButton("Zapisz dane");
        dodajDane.setBounds(600, 740,150,40);
        add(dodajDane);
        dodajDane.addActionListener(e -> {
            int kodpocztowyI = Integer.parseInt(kodPocztowy1J.getText())*1000+Integer.parseInt(kodPocztowy2J.getText());
            String dataUrodzeniaS = lataJ.getSelectedItem()+"-"+miesaceJ.getSelectedItem()+"-"+dniJ.getSelectedItem();
            aktualizujKlient(imieJ,nazwiskoJ,plecJ,dataUrodzeniaS,peselJ,wojewodztwoJ,stanCywilnyJ,dzieciJ,kodpocztowyI,prawoJazdyJ,sposobUzytkowaniaJ);
            System.out.println(start.getKlient());
        });

        repaint();
    }

    public void wprowadzPojazd(){
        getContentPane().removeAll();

        JLabel tytul = new JLabel("Uzupełnij formularze");
        tytul.setFont(new Font("Arial",Font.BOLD,30));
        tytul.setBounds(0,30,600,100);
        tytul.setHorizontalAlignment(JLabel.CENTER);
        add(tytul);


        String[] s = new String[start.getMarki().length];
        for(int i = 0; i < start.getMarki().length; i++){
            s[i] = start.getMarki()[i].getNazwa();
        }
        JLabel marka = new JLabel("Wybierz markę pojazdu");
        marka.setBounds(50,200,150, 40);
        marka.setHorizontalAlignment(JLabel.CENTER);
        add(marka);
        JComboBox listaMarek = new JComboBox<>(s);
        listaMarek.setBounds(210, 200, 80, 40);
        listaMarek.setEditable(false);
        add(listaMarek);

        JLabel model = new JLabel("Wybierz model pojazdu");
        model.setBounds(50,260,150, 40);
        model.setHorizontalAlignment(JLabel.CENTER);
        add(model);
        JComboBox listaModeli = new JComboBox<>();
        listaModeli.setBounds(210,260, 80,40);
        setModelList(start.getMarki()[0].getNazwa(), listaModeli);
        add(listaModeli);

        JLabel pojemnosc = new JLabel("Wpisz pojemnosc silnika");
        pojemnosc.setBounds(50,320,150, 40);
        pojemnosc.setHorizontalAlignment(JLabel.CENTER);
        add(pojemnosc);
        JTextField pojemnoscSilnika = new JTextField();
        pojemnoscSilnika.setBounds(210,320,80,40);
        add(pojemnoscSilnika);

        JLabel rok = new JLabel("Wpisz rok produkcji");
        rok.setBounds(50,380,150, 40);
        rok.setHorizontalAlignment(JLabel.CENTER);
        add(rok);
        JTextField rokProdukcji = new JTextField();
        rokProdukcji.setBounds(210,380,80,40);
        add(rokProdukcji);

        JLabel przebieg = new JLabel("Wpisz przebig pojazdu");
        przebieg.setBounds(50,440,150, 40);
        przebieg.setHorizontalAlignment(JLabel.CENTER);
        add(przebieg);
        JTextField przebiegPojazdu = new JTextField();
        przebiegPojazdu.setBounds(210,440,80,40);
        add(przebiegPojazdu);

        JLabel paliwo = new JLabel("Wybierz rodzaj paliwa");
        paliwo.setBounds(50,500,150, 40);
        paliwo.setHorizontalAlignment(JLabel.CENTER);
        add(paliwo);
        String[] paliwa = {"benzyna","diesel"};
        JComboBox rodzajPaliwa = new JComboBox<>(paliwa);
        rodzajPaliwa.setBounds(210,500,80,40);
        rodzajPaliwa.setEditable(false);
        add(rodzajPaliwa);

        String[] uszkodzniaS = {"Obtarcie","Stłuczka","Szkoda całkowita"};
        JComboBox uszkodzenia = new JComboBox<>(uszkodzniaS);
        uszkodzenia.setBounds(250,560,150,40);

        JLabel uszkodzeniabBox = new JLabel("Czy auto jest uszkodzone");
        uszkodzeniabBox.setBounds(50,560,150,40);
        add(uszkodzeniabBox);
        JCheckBox uszkodzeniaZaznacz = new JCheckBox();
        uszkodzeniaZaznacz.setBounds(210,560,40,40);
        add(uszkodzeniaZaznacz);
        uszkodzeniaZaznacz.addActionListener(e -> {
            if(uszkodzeniaZaznacz.isSelected()){
                add(uszkodzenia);
                repaint();
            }else{
                remove(uszkodzenia);
                repaint();
            }
        });

        JLabel rejestracja = new JLabel("Wprowadź nr rejestracji");
        rejestracja.setBounds(50,620,150, 40);
        rejestracja.setHorizontalAlignment(JLabel.CENTER);
        add(rejestracja);
        JTextField rejestracjaF = new JTextField();
        rejestracjaF.setBounds(210,620,80,40);
        add(rejestracjaF);


        JButton upPojazd = new JButton("Zapisz dane");
        upPojazd.setBounds(50,680,150,50);
        upPojazd.addActionListener(e -> {
            danePojazdu = true;
            aktualizujPojazd(listaMarek,listaModeli,rodzajPaliwa,pojemnoscSilnika,przebiegPojazdu,rokProdukcji,uszkodzenia,rejestracjaF);
        });
        add(upPojazd);

        JButton przycisk = new JButton();
        przycisk.setBounds(50,740,80,40);
        przycisk.setText("Drukuj");

        JButton lizcOC = new JButton("Oblicz oc");

        //Przycisk powrotu
        JButton powrot = new JButton("Wróć");
        powrot.setBounds(985,0,100,50);
        powrot.setHorizontalAlignment(JButton.CENTER);
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
        this.daneKlienta = false;
        this.danePojazdu = false;
        this.daneUbezpieczenia = false;
        this.opcjeUbezpieczenia = new boolean[4];

        //JScrollPane scr = new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //add(scr);

        JLabel tytul = new JLabel("Porównywarka ubezpieczeń");
        tytul.setBounds(283,10,500,30);
        tytul.setFont(new Font("Arial",Font.BOLD, 30));
        tytul.setHorizontalAlignment(JLabel.CENTER);
        setBackground(new Color(255,255,255));

        setTitle("Ubezpieczenia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1100,900);
        setResizable(false);
        setVisible(true);

        add(tytul);
        widokStart();
        setVisible(true);

    }

    public void pokazOferty(){

        String rodzajU = "";

        JFrame oferty = new JFrame();
        oferty.setVisible(true);
        oferty.setSize(600,800);

        JLabel ocN = new JLabel("OC");
        JLabel acN = new JLabel("AC");
        JLabel nwN = new JLabel("NNW");
        JLabel asN = new JLabel("ASS");
        ocN.setBounds(270,5,40,30);
        acN.setBounds(320,5,40,30);
        nwN.setBounds(370,5,40,30);
        asN.setBounds(420,5,40,30);
        ocN.setHorizontalAlignment(JLabel.CENTER);
        acN.setHorizontalAlignment(JLabel.CENTER);
        nwN.setHorizontalAlignment(JLabel.CENTER);
        asN.setHorizontalAlignment(JLabel.CENTER);
        oferty.add(ocN);
        oferty.add(acN);
        oferty.add(nwN);
        oferty.add(asN);

        int liczbaOfert = start.getUbezpieczyciel().length;
        for(int i = 0; i < liczbaOfert ; i++){

            JButton wybierz = new JButton("Wybierz");
            wybierz.setBounds(480, (50 + i * 50),80,40);
            oferty.add(wybierz);

            JCheckBox ocP = new JCheckBox();
            JCheckBox acP = new JCheckBox();
            JCheckBox nwP = new JCheckBox();
            JCheckBox asP = new JCheckBox();
            ocP.setHorizontalAlignment(JCheckBox.CENTER);
            acP.setHorizontalAlignment(JCheckBox.CENTER);
            nwP.setHorizontalAlignment(JCheckBox.CENTER);
            asP.setHorizontalAlignment(JCheckBox.CENTER);
            ocP.setEnabled(false);
            acP.setEnabled(false);
            nwP.setEnabled(false);
            asP.setEnabled(false);
            ocP.setBounds(270,(50 + i * 50),40,40);
            acP.setBounds(320,(50 + i * 50),40,40);
            nwP.setBounds(370,(50 + i * 50),40,40);
            asP.setBounds(420,(50 + i * 50),40,40);

            int suma = 0;

            boolean samoAC =  false;

            if(opcjeUbezpieczenia[0]){
                ocP.setSelected(true);
                suma += start.getUbezpieczyciel()[i].getPrzeliczniki().liczOC(start.getPojazd(), start.getMarki(), start.getKlient());
                rodzajU += "OC, ";
            }
            else samoAC = true;
            if(opcjeUbezpieczenia[1]){

                int tmp = (int) start.getUbezpieczyciel()[i].getPrzeliczniki().liczAC(start.getPojazd(),start.getMarki(), start.getKlient(), samoAC);
                if(tmp!=0){
                    suma += tmp;
                    acP.setSelected(true);
                    rodzajU += "AC, ";
                }
            }
            if(opcjeUbezpieczenia[2]){
                nwP.setSelected(true);
                suma += start.getUbezpieczyciel()[i].getNNW();
                rodzajU += "NNW, ";
            }
            if(opcjeUbezpieczenia[3]){
                asP.setSelected(true);
                suma += start.getUbezpieczyciel()[i].getAss();
                rodzajU += "Assistance";
            }

            JTextField ubezpieczyciel = new JTextField();
            ubezpieczyciel.setText(start.getUbezpieczyciel()[i].getNazwa());
            ubezpieczyciel.setFont(new Font("Arial",Font.BOLD,15));
            ubezpieczyciel.setEditable(false);
            ubezpieczyciel.setBounds(45, (50 + i * 50),100,40);
            ubezpieczyciel.setHorizontalAlignment(JTextField.CENTER);
            oferty.add(ubezpieczyciel);

            JTextField kwota = new JTextField();
            kwota.setText(suma+" zł");
            kwota.setEditable(false);
            kwota.setBounds(155, (50 + i * 50),100,40);
            kwota.setHorizontalAlignment(JTextField.CENTER);
            oferty.add(kwota);

            oferty.add(ocP);
            oferty.add(acP);
            oferty.add(nwP);
            oferty.add(asP);

            String finalRodzajU = rodzajU;
            wybierz.addActionListener(e -> {
                start.getUbezpieczenie().setNazwa(ubezpieczyciel.getText());
                start.getUbezpieczenie().setRodzaj(finalRodzajU);
                start.getUbezpieczenie().setCena(kwota.getText());
                Date date = new Date();
                start.getUbezpieczenie().setDataRozpoczecia(date);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, 1);
                Date nextYear = cal.getTime();
                start.getUbezpieczenie().setDataZakonczenia(nextYear);
                start.getUbezpieczenie().setPeselKlienta(start.getKlient().getPesel()+"");
                start.getUbezpieczenie().setNrRejestracyjny(start.getPojazd().getNrRejestracyjny());
                System.out.println(start.getUbezpieczenie());

            });

            JLabel buff = new JLabel();
            buff.setVisible(false);
            oferty.add(buff);

        }
        oferty.repaint();
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

    public void aktualizujKlient(JTextField imie, JTextField nazwisko, JComboBox plec, String dataUrodzenia, JTextField pesel, JComboBox wojewodztwo, JComboBox stanCywilny, JComboBox czyDzieci, int kodPocztowy, JTextField rokPrawaJazdy, JComboBox sposobUzytkowania){
        if(start.getKlient() == null){
            start.setKlient(new Klient());
        }
        Date now = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        start.getKlient().setImie(imie.getText());
        start.getKlient().setNazwisko(nazwisko.getText());
        start.getKlient().setPlec(Klient.rodzajePlci.valueOf((String) plec.getSelectedItem()));
        try {
            start.getKlient().setDataUrodzenia(sd.parse(dataUrodzenia));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        start.getKlient().setPesel(Long.parseLong(pesel.getText()));
        start.getKlient().setNrWojewodztwa(wojewodztwo.getSelectedIndex());
        start.getKlient().setStanCywilny(Klient.rodzajeStanowCywilnych.valueOf((String) stanCywilny.getSelectedItem()));
        start.getKlient().setStanCywil(stanCywilny.getSelectedIndex());
        boolean dzieci = false;
        if(czyDzieci.getSelectedIndex()==0)dzieci = true;
        start.getKlient().setCzyDzieci(dzieci);
        start.getKlient().setKodPocztowy(kodPocztowy);
        start.getKlient().setRokOtrzymaniaPrawaJazdy(Integer.parseInt(rokPrawaJazdy.getText()));
        start.getKlient().setSposobUzytkowania(sposobUzytkowania.getSelectedIndex());

        daneKlienta = true;
    }

    public void aktualizujPojazd(JComboBox listaMarek, JComboBox listaModeli, JComboBox rodzajP, JTextField pojemnoscSilnika, JTextField przebiegPojazdu, JTextField rokProdukcjiPojazdu, JComboBox uszkodzenia, JTextField rejestracja){

        String marka = (String)listaMarek.getSelectedItem();
        String model = (String)listaModeli.getSelectedItem();
        String rodzajPaliwa = (String)rodzajP.getSelectedItem();
        double pojemnosc = Double.parseDouble(pojemnoscSilnika.getText());
        int przebieg = Integer.parseInt(przebiegPojazdu.getText());
        int rokProdukcji = Integer.parseInt(rokProdukcjiPojazdu.getText());
        int uszkodzeniaI = 0;
        if(uszkodzenia.getSelectedItem().toString().equals("Obtarcie")) uszkodzeniaI = 25;
        else if(uszkodzenia.getSelectedItem().equals("Stłuczka")) uszkodzeniaI = 50;
        else if(uszkodzenia.getSelectedItem().toString().equals("Szkoda całkowita")) uszkodzeniaI = 100;
        String rej = rejestracja.getText();
        start.getPojazd().setMarka(marka);
        start.getPojazd().setModel(model);
        start.getPojazd().setRodzaj_paliwa(rodzajPaliwa);
        start.getPojazd().setPojemnosc_silnika(pojemnosc);
        start.getPojazd().setPrzebieg(przebieg);
        start.getPojazd().setRok_produkcji(rokProdukcji);
        start.getPojazd().setStopien_uszkodzen(uszkodzeniaI);
        start.getPojazd().setNrRejestracyjny(rej);
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