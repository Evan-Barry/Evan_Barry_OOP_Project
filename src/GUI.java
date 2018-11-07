import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI extends JFrame implements ActionListener{

    JMenu gameMenu;
    JLabel message;
    Container cPane;
    JPanel topPanel, bottomPanel;
    JPanel t1, t2, t3, b1, b2, b3;
    Dimension screenSize;
    JLabel imageLabel;

    public GUI()
    {
        setTitle("Blackjack");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        setResizable(false);

        cPane = getContentPane();
        cPane.setLayout(new GridLayout(2,1));
        cPane.setBackground(new Color(39,119,20));


        topPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight()/2);
        bottomPanel.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight()/2);

        topPanel.setLocation(0,0);
        bottomPanel.setLocation(0, (int)screenSize.getHeight()/2);

        cPane.add(topPanel);
        cPane.add(bottomPanel);

        topPanel.setLayout(new GridLayout(1,3));
        bottomPanel.setLayout(new GridLayout(1,3));

        t1 = new JPanel();
        t2 = new JPanel();
        t3 = new JPanel();

//        t1.setSize((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/2);
//        t2.setSize((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/2);
//        t3.setSize((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/2);

        t1.setBackground(cPane.getBackground());
        t2.setBackground(cPane.getBackground());
        t3.setBackground(cPane.getBackground());

        topPanel.add(t1);
        topPanel.add(t2);
        topPanel.add(t3);

        b1 = new JPanel();
        b2 = new JPanel();
        b3 = new JPanel();

//        b1.setSize((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/2);
//        b2.setSize((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/2);
//        b3.setSize((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/2);

        b1.setBackground(cPane.getBackground());
        b2.setBackground(cPane.getBackground());
        b3.setBackground(cPane.getBackground());

        bottomPanel.add(b1);
        bottomPanel.add(b2);
        bottomPanel.add(b3);

        createGameMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(gameMenu);

        message = new JLabel("Welcome");
        message.setFont(new Font("SansSerif",Font.PLAIN,30));
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment((JLabel.TOP));
        t2.add(message);

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
            displayHands(d);
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

        message.setText("Dealer");
        message.setHorizontalAlignment((JLabel.CENTER));
        message.setVerticalAlignment(((JLabel.CENTER)));
        t1.add(message);

        message.setText(h1.getName());
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment((JLabel.CENTER));
        b1.add(message);

        repaint();

        Blackjack b1 = new Blackjack(numberOfDecks);

        return deck;
    }

    private void displayHands(Deck d)
    {
        //System.out.println(d.toString());

        Card[] cardArray = d.getCards();

        ImageIcon image;
        for(int i = 0; i < 2; i++)
        {
            String cardName = cardArray[i].getValue() + cardArray[i].getSuit();

            image = new ImageIcon("resources/" + cardName + ".png");
            imageLabel = new JLabel(image);
            imageLabel.setVisible(true);
            imageLabel.setHorizontalAlignment(JLabel.LEFT );
            b2.add(imageLabel);
        }

    }

}
