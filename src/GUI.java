import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI()
    {
        super("This is the game window");
        setSize(750,750);
        setLocation(200,0);
        getContentPane().setBackground(new Color(39, 119, 20));
        setResizable(false);

        JButton newGame = new JButton("New Game");
        //newGame.setLocation(325,50);
        //newGame.setSize(100,25);

        getContentPane().add(newGame);
    }

}
