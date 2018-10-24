import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    JButton newGameButton;

    public GUI()
    {
        super("This is the game window");
        setSize(750,750);
        setLocation(200,0);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(39, 119, 20));
        newGameButton = new JButton("New Game");
        newGameButton.setLocation(325,700);
        add(newGameButton);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
