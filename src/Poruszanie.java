import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Poruszanie extends Wyglad
{
    Show_Time show_time;
    File file;
    long elapsed;
    private long stopTime, startTime;
    private boolean flag_time;
    private boolean end_game;
    private NowyButon[] tab_buttons = new NowyButon[16];
    private JLabel disp_time, the_best_time;

    /**
     * konstruktor w którym przypisane są przyciski do zmiany
     * @param tab_buttons
     * @param disp_time
     * @param the_best_time
     */
    public Poruszanie(NowyButon[] tab_buttons, JLabel disp_time, JLabel the_best_time)
    {
        this.tab_buttons = tab_buttons;
        this.disp_time = disp_time;
        this.the_best_time = the_best_time;
        flag_time = true;
        end_game = true;
        show_time = new Show_Time();
        show_time.start();
        set_score_time();
    }

    /**
     * metoda zmiany koloru czerwonego przy zdarzeniu
     * @param e
     */
    public void actionPerformed(ActionEvent e)
    {
        if (!end_game) return;
        Object zrodlo = e.getSource();
        NowyButon button = (NowyButon) zrodlo;
        int position = button.getPosition_button();
        if (position + 1 == red_index)
        {
            if ((position + 1 != 4) && (position + 1 != 8) && (position + 1 != 12))
            {
                set_button(position);
            }
        } else if (position - 1 == red_index)
        {
            if ((position - 1 != 3) && (position - 1 != 7) && (position - 1 != 11))
                set_button(position);
        } else if (position + 4 == red_index)
        {
            set_button(position);
        } else if (position - 4 == red_index)
        {
            set_button(position);
        }
        check_result();
    }

    public void set_score_time()
    {
        the_best_time.setText("best: " + get_the_best_time());
    }

    public void set_the_best_time()
    {
        FileWriter fileWriter = null;
        String time = get_the_best_time();

        if (time == null || Integer.parseInt(time) > elapsed)
        {
            try
            {
                fileWriter = new FileWriter("score_time.txt", false);
            } catch (IOException e)
            {
                System.out.println("problem z plikiem");
                return;
            }
            BufferedWriter bw = new BufferedWriter(fileWriter);
            try
            {
                bw.write(String.valueOf(elapsed));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                bw.close();
                fileWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public String get_the_best_time()
    {
        String line = "";

        BufferedReader brIn = null;
        try
        {
            brIn = new BufferedReader(new FileReader("score_time.txt"));
            line = brIn.readLine();
            brIn.close();
        } catch (FileNotFoundException e)
        {
            try
            {
                PrintWriter writer = new PrintWriter("score_time.txt", "UTF-8");
                writer.close();
            } catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1)
            {
                e1.printStackTrace();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return line;
    }

    public void set_button(int index_button_change)
    {
        tab_buttons[red_index].setBackground_button(Color.WHITE);
        tab_buttons[red_index].setTextNumber_button(tab_buttons[index_button_change].getNumber_button());
        red_index = index_button_change;
        tab_buttons[index_button_change].setBackground_button(Color.red);
        tab_buttons[index_button_change].setTextNumber_button("");
    }

    public void check_result()
    {
        boolean flag = true;
        for (int i = 0; i <= 14; i++)
        {
            if (!(tab_buttons[i].getNumber_button().equals(String.valueOf(i + 1)))) flag = false;
        }
        if (flag)
        {
            System.out.println("Gratulacje wygrales!");
            clock_stop(true);
            end_game = false;
        }
    }

    public void clock_start()
    {
        this.startTime = System.currentTimeMillis();
    }

    public void clock_stop(boolean flag_end_game)
    {
        this.stopTime = System.currentTimeMillis();
        flag_time = false;
        tab_buttons[red_index].setBackground_button(Color.WHITE);
        if (flag_end_game)
        {
            set_the_best_time();
        }
    }

    class Show_Time extends Thread
    {

        public void run()
        {
            clock_start();
            while (flag_time)
            {
                elapsed = (System.currentTimeMillis() - startTime) / 1000;
                disp_time.setText("Czas: " + elapsed);
            }
            if (!flag_time)
            {
                elapsed = (stopTime - startTime) / 1000;
                disp_time.setText("twoj czas: " + elapsed);
            }
        }
    }
}
