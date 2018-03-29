import java.awt.*;
import java.awt.event.ActionEvent;

public class Poruszanie extends Wyglad{
    private NowyButon[] tab_buttons = new NowyButon[16];

    public Poruszanie(NowyButon[] tab_buttons){
        this.tab_buttons = tab_buttons;
    }
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        NowyButon button = (NowyButon) zrodlo;
        int pozycja = button.getPosition_button();
        if(pozycja + 1 == indeks_czerwonego) {
            ustaw_przyciski(pozycja);
        }
        else if(pozycja - 1 == indeks_czerwonego){
            ustaw_przyciski(pozycja);
        }
        else if(pozycja + 4 == indeks_czerwonego){
            ustaw_przyciski(pozycja);
        }
        else if(pozycja - 4 == indeks_czerwonego){
            ustaw_przyciski(pozycja);
        }
        sprawdz_wynik();
    }

    public void ustaw_przyciski(int indeks_przycisku_zamiany){
        tab_buttons[indeks_czerwonego].setBackground_button(Color.WHITE);
        tab_buttons[indeks_czerwonego].setTextNumber_button(tab_buttons[indeks_przycisku_zamiany].getNumber_button());
        indeks_czerwonego = indeks_przycisku_zamiany;
        tab_buttons[indeks_przycisku_zamiany].setBackground_button(Color.red);
        tab_buttons[indeks_przycisku_zamiany].setTextNumber_button("");
    }
    public void sprawdz_wynik(){
        for (int i = 0; i <=15; i++){
            if(!tab_buttons[i].getNumber_button().equals(String.valueOf(i))) return;
        }
        System.out.println("Gratulacje wygrales!");
    }

}
