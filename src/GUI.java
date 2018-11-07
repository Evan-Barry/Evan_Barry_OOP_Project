import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI extends JFrame implements ActionListener{

    JMenu gameMenu;
    JLabel message;
    Container cPane;
    Dimension screenSize;

    public GUI()
    {
        setTitle("Blackjack");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
        setLocation((int)screenSize.getWidth()/4,(int)screenSize.getHeight()/4);
        setResizable(false);

        cPane = getContentPane();
        cPane.setLayout(new GridLayout());
        cPane.setBackground(new Color(39,119,20));

        createGameMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(gameMenu);

        message = new JLabel("Welcome");
        message.setFont(new Font("SansSerif",Font.PLAIN,30));
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment((JLabel.TOP));
        cPane.add(message);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String menuName;
        menuName = e.getActionCommand();

        if(menuName.equals("Quit"))
        {
            System.exit(0);
        }

        else if(menuName.equals("New Game"))
        {
            Deck d = setUpGame();
            d.shuffle();
            //displayHands(d);
        }

//
//
//        else if(e.getSource() == blackjackButton)
//        {
//            clearButtons();
////                blackjack
//
////                Card c = new Card(3,12);
////
////                ImageIcon image;
////                JLabel imageLabel;
////                String cardName = c.getValue() + c.getSuit();
////
////
////
////                image = new ImageIcon("C:\\Users\\t00202376\\Desktop\\" + cardName + ".png");
////                imageLabel = new JLabel(image);
////                imageLabel.setVisible(true);
////                add(imageLabel);
//
//
//
//            System.out.println(d1.toString());
//
//            d1.shuffle(d1.getCards());
//
//            System.out.println(d1.toString());
//        }
    }


    private void createGameMenu()
    {
        JMenuItem item;

        gameMenu = new JMenu("Game");

        item = new JMenuItem("New Game");
        item.setMnemonic(KeyEvent.VK_N);
        item.addActionListener(this);
        gameMenu.add(item);

        gameMenu.addSeparator();

        item = new JMenuItem("Quit");
        item.setMnemonic(KeyEvent.VK_Q);
        item.addActionListener(this);
        gameMenu.add(item);
    }

    private Deck setUpGame()
    {
        String name = JOptionPane.showInputDialog("What is your name?");
        Human h1 = new Human(name);

        Dealer d1 = new Dealer();

        int numberOfDecks = Integer.parseInt(JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))"));
        Deck deck = new Deck(numberOfDecks);

        message.setText("\t\t" + h1.getName());
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        message.setHorizontalAlignment(JLabel.LEFT);
        message.setVerticalAlignment((JLabel.BOTTOM));
        cPane = getContentPane();
        cPane.add(message);

        Blackjack b1 = new Blackjack(numberOfDecks);

        return deck;
    }

//    private void displayHands(Deck d)
//    {
//        Card c = new Card(0,0);
//
//        for(int i = 0; i < 2; i++)
//        {
//            d.getC(i);
//            System.out.println(c.toString());
//        }
//    }

}
