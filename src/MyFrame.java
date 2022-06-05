import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
    JButton przycisk;
    JComboBox lista;

    private void ustawListe(MarkaPojazdu[] marka){
        String[] s = new String[marka.length];
        for(int i = 0; i < marka.length; i++){
            s[i] = marka[i].getNazwa();
        }
        lista = new JComboBox(s);
    }

    public MyFrame(MarkaPojazdu[] marki, Pojazd pojazd){

        ustawListe(marki);
        przycisk = new JButton();
        przycisk.setBounds(0,0,100,100);
        przycisk.setText("Drukuj");
        lista.addActionListener(e -> pojazd.setMarka((String) lista.getSelectedItem()));
        przycisk.addActionListener(e -> System.out.println(pojazd.getMarka()));

        this.setTitle("Ubezpieczenia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);
        this.add(lista);
        this.add(przycisk);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==lista){

        }
        if(e.getSource()==przycisk){

        }
    }
}
