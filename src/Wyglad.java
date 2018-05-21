import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Wyglad extends JFrame implements ActionListener
{

    Poruszanie poruszanie = null;
    private NowyButon[] tab_buttons = new NowyButon[16];
    private NowyButon new_game;
    private JLabel disp_time, the_best_time;
    static int red_index;

    /**
     * konstruktor wyglądu graficznego interfejsu
     */
    public Wyglad()
    {
        setSize(418, 490);
        setTitle("Puzzle Game");
        setLayout(null);
        new_game = new NowyButon("nowa gra", 0);
        new_game.setBounds(164, 5, 90, 30);
        add(new_game);
        new_game.addActionListener(this);
        disp_time = new JLabel("Czas:   0");
        disp_time.setBounds(25, 5, 95, 30);
        add(disp_time);
        the_best_time = new JLabel("najlepszy czas: ");
        the_best_time.setBounds(275, 5, 95, 30);
        add(the_best_time);

        int x = 0;
        int y = 50;
        for (int i = 1; i <= 16; i++)
        {
            if (x < 400)
            {
                tab_buttons[i - 1] = build_buttons(x, y, String.valueOf(i), tab_buttons[i - 1], i);
                x += 100;
            } else
            {
                x = 0;
                y += 100;
                tab_buttons[i - 1] = build_buttons(x, y, String.valueOf(i), tab_buttons[i - 1], i);
                x += 100;
            }
        }
    }

    /**
     * funkcja tworząca nowe przyciski poruszania
     *
     * @param x      wartos osi x
     * @param y      wartosc osi y
     * @param name   nazwa przycisku 1,2..16
     * @param button przycisk
     * @param i      numer przycisku
     * @return       zwracanie poprawnie ustawionego przycisku
     */
    public NowyButon build_buttons(int x, int y, String name, NowyButon button, int i)
    {
        if (name.equals("16"))
        {
            button = new NowyButon("", i - 1);
        } else
        {
            button = new NowyButon(name, i - 1);
        }
        button.setBounds(x, y, 100, 100);
        add(button);
        button.addActionListener(this);
        return button;
    }

    /**
     * pobieranie przycisku akcji
     * dodanie go do metod w kasie poruszanie
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source_button = e.getSource();
        NowyButon button = (NowyButon) source_button;
        if (button.getText().equals("nowa gra"))
        {
            poruszanie.clock_stop(false);
            poruszanie = null;
            lottery();
        } else
            poruszanie.actionPerformed(e);
    }


    public void lottery()
    {
        Random rand = new Random();
        red_index = rand.nextInt(16);
        List<Integer> a = new ArrayList<Integer>(16);
        for (int i = 1; i <= 15; i++)
        {
            a.add(i);
        }
        Collections.shuffle(a);
        write_red();
        for (int i = 0; i <= 15; i++)
        {
            if (a.isEmpty()) break;
            if (write_blue(a.get(0), i))
            {
                a.remove(0);
            }
        }
        poruszanie = new Poruszanie(tab_buttons, disp_time, the_best_time);
    }

    public void write_red()
    {
        //najpierw przypisujemy wartosc czerwonego
        tab_buttons[red_index].setTextNumber_button("");
        tab_buttons[red_index].setBackground_button(Color.red);
    }

    public Boolean write_blue(int liczba, int indeks_tablicy_button)
    {
        if (!(tab_buttons[indeks_tablicy_button].getBackground_button() == Color.red))
        {
            tab_buttons[indeks_tablicy_button].setTextNumber_button(String.valueOf(liczba));
            return true;
        } else
            return false;
    }

    static
    {
        red_index = 0;
    }
}
