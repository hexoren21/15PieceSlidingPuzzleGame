import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Wyglad extends JFrame implements ActionListener{
    private NowyButon[] tab_buttons = new NowyButon[16];
    static int polozenie_czerwonego = 0;
    public Wyglad(){
        setSize(418, 440);
        setTitle("Puzzle Game");
        setLayout(null);
        int x = 0, y =0;
        for(int i = 1; i <= 16; i++){
            if(x < 400) {
                tab_buttons[i-1] = tworz_przycisk(x, y, String.valueOf(i), tab_buttons[i-1]);
                x += 100;
            }
            else   {
                x = 0;
                y += 100;
                tab_buttons[i-1] = tworz_przycisk(x, y, String.valueOf(i), tab_buttons[i-1]);
                x += 100;
            }
        }
    }
    public NowyButon tworz_przycisk(int x, int y, String name, NowyButon button){
        if(name.equals("16")){
            System.out.println("brak coloru");
            button = new NowyButon("");
         }
        else {
            button = new NowyButon(name);
        }
        button.setBounds(x,y,100,100);
        add(button);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();

    }


    public void losowanie(){
        Random rand = new Random();
        polozenie_czerwonego = rand.nextInt(16);
        List <Integer> a = new ArrayList<Integer>(16);
        for(int i = 1; i<=15; i++){
            a.add(i);
        }
        Collections.shuffle(a);
        przypisz_czerwony();
        for(int i = 0; i <= 15; i++) {
          if(przypisz_niebieski(a.get(0), i)){
               a.remove(0);
            }
        }

       // for (int i : a) System.out.printf("%d \n",i)
    }
    public void przypisz_czerwony() {
        //najpierw przypisujemy wartosc czerwonego
        tab_buttons[polozenie_czerwonego].setTextNumber_button("");
        tab_buttons[polozenie_czerwonego].setBackground_button(Color.pink);
    }
     public Boolean przypisz_niebieski(int liczba, int indeks_tablicy_button) {

         System.out.printf("%d - %s\n",indeks_tablicy_button, String.valueOf(tab_buttons[indeks_tablicy_button].getBackground_button()));
         if(!(tab_buttons[indeks_tablicy_button].getBackground_button() == Color.pink)){
            tab_buttons[indeks_tablicy_button].setTextNumber_button(String.valueOf(liczba));
            return true;
         }
        else
            return false;

    }
    static {
        polozenie_czerwonego = 0;
    }
}
