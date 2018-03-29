import javafx.scene.layout.Background;

import javax.swing.*;
import java.awt.*;

public class NowyButon extends JButton {
    private int position_button = 0;
    private Color background_button=null;
    private String text_number_button=null;
    NowyButon(String name, int i){
        super(name);
        position_button = i;
        text_number_button = name;
        background_button = Color.WHITE;
        this.setBackground(Color.WHITE);
    }
    public Color getBackground_button(){
        return background_button;
    }
    public String getNumber_button(){
        return text_number_button;
    }
    public void  setBackground_button(Color color){
        this.background_button = color;
        this.setBackground(color);
    }
    public void  setTextNumber_button(String number){
        this.text_number_button = number;
        this.setText(number);
    }
    public int getPosition_button(){
        return position_button;
    }
}
