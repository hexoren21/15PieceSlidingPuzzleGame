import javax.swing.*;

public class Main {
    public static void main(String args[]){
        Wyglad gra = new Wyglad();
        gra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gra.setVisible(true);
        gra.losowanie();
    }
}
