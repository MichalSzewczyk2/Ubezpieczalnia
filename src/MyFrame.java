import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
    JButton przycisk;
    JComboBox listaMarek;
    JComboBox listaModeli;

    private void ustawListe(MarkaPojazdu[] marka){
        String[] s = new String[marka.length];
        for(int i = 0; i < marka.length; i++){
            s[i] = marka[i].getNazwa();
        }
        listaMarek = new JComboBox(s);
    }

    public MyFrame(MarkaPojazdu[] marki, Pojazd pojazd){

        ustawListe(marki);
        przycisk = new JButton();
        przycisk.setBounds(0,0,100,100);
        przycisk.setText("Drukuj");
        listaModeli = new JComboBox();
        listaMarek.addActionListener(e -> this.setModelList(pojazd,((String) listaMarek.getSelectedItem()),marki));
        przycisk.addActionListener(e -> System.out.println(pojazd.getMarka()));

        this.setTitle("Ubezpieczenia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);
        this.add(listaMarek);
        this.add(przycisk);
        this.add(listaModeli);

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
        listaModeli = new JComboBox(s);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==listaMarek){

        }
        if(e.getSource()==przycisk){

        }
    }
}
