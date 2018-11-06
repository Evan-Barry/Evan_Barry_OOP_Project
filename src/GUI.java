import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    JButton newGameButton, pokerButton, blackjackButton, goFishButton;
    boolean newGameButtonPressed = false;

    public GUI()
    {
        super("This is the game window");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        setLocation(0,0);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(39, 119, 20));
        newGameButton = new JButton("New Game");
        newGameButton.setMnemonic(KeyEvent.VK_N);
        newGameButton.setLocation(325,700);
        newGameButton.addActionListener(new ButtonListener());
        add(newGameButton);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == newGameButton && newGameButtonPressed == false)
            {
                newGameButton.setVisible(false);

                pokerButton = new JButton("Poker");
                pokerButton.setMnemonic(KeyEvent.VK_P);
                pokerButton.addActionListener(new ButtonListener());
                add(pokerButton);

                blackjackButton = new JButton("Blackjack");
                pokerButton.setMnemonic(KeyEvent.VK_B);
                blackjackButton.addActionListener(new ButtonListener());
                add(blackjackButton);

                goFishButton = new JButton("Go Fish");
                goFishButton.setMnemonic(KeyEvent.VK_G);
                goFishButton.addActionListener(new ButtonListener());
                add(goFishButton);

                newGameButtonPressed = true;

                revalidate();
                repaint();
            }

            else if(e.getSource() == pokerButton)
            {
                clearButtons();
            }

            else if(e.getSource() == blackjackButton)
            {
                clearButtons();
//                blackjack

//                Card c = new Card(3,12);
//
//                ImageIcon image;
//                JLabel imageLabel;
//                String cardName = c.getValue() + c.getSuit();
//
//
//
//                image = new ImageIcon("C:\\Users\\t00202376\\Desktop\\" + cardName + ".png");
//                imageLabel = new JLabel(image);
//                imageLabel.setVisible(true);
//                add(imageLabel);

                String name = JOptionPane.showInputDialog("What is your name?");
                Human h1 = new Human(name);

                int numberOfDecks = Integer.parseInt(JOptionPane.showInputDialog("How many decks do you want to play with?"));
                Deck d1 = new Deck(numberOfDecks);

                System.out.println(d1.toString());

                d1.shuffle(d1.getCards());

                System.out.println(d1.toString());
            }

            else if(e.getSource() == goFishButton)
            {
                clearButtons();
            }
        }
    }

    public void clearButtons()
    {
        newGameButton.setVisible(false);
        pokerButton.setVisible(false);
        blackjackButton.setVisible(false);
        goFishButton.setVisible(false);
    }

}
