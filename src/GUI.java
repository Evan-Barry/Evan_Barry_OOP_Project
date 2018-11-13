import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{

    JMenu gameMenu;
    JLabel message;
    Container cPane;
    JPanel topPanel, bottomPanel;
    JPanel t1, t2, t3, b1, b2, b3;
    JPanel b3r1, b3r2, b3r3;
    Dimension screenSize;
    JLabel imageLabel;
    JButton surrenderButton;
    ImageIcon image;

    Deck deck;
    ArrayList<Card> playerHand;
    ArrayList<Card> cardArrayList;

    Human h1;
    Dealer d1;
    Blackjack blackjack;

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

        t1.setBackground(cPane.getBackground());
        t2.setBackground(cPane.getBackground());
        t3.setBackground(cPane.getBackground());

//        t1.setBackground(new Color(255,0,0));
//        t2.setBackground(new Color(0,255,0));
//        t3.setBackground(new Color(0,0,255));

        topPanel.add(t1);
        topPanel.add(t2);
        topPanel.add(t3);

        b1 = new JPanel();
        b2 = new JPanel();
        b3 = new JPanel();

        b1.setBackground(cPane.getBackground());
        b2.setBackground(cPane.getBackground());
        b3.setBackground(cPane.getBackground());

//        b1.setBackground(new Color(255,255,0));
//        b2.setBackground(new Color(0,255,255));
//        b3.setBackground(new Color(255,0,255));

        bottomPanel.add(b1);
        bottomPanel.add(b2);
        bottomPanel.add(b3);

        b3.setLayout(new GridLayout(3,1));

        b3r1 = new JPanel();
        b3r2 = new JPanel();
        b3r3 = new JPanel();

        b3r1.setBackground(cPane.getBackground());
        b3r2.setBackground(cPane.getBackground());
        b3r3.setBackground(cPane.getBackground());

        b3.add(b3r1);
        b3.add(b3r2);
        b3.add(b3r3);

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


        if(e.getActionCommand().equals("Quit"))
        {
            System.exit(0);
        }

        else if(e.getActionCommand().equals("New Game"))
        {
            h1 = new Human();
            d1 = new Dealer();
            deck = setUpGame();
            deck.shuffle();
            displayHands(deck);
            addButtons();
        }

        else if(e.getActionCommand().equals("Hit"))
        {
            System.out.println("Player Hit");
            surrenderButton.setEnabled(false);
            System.out.println(h1.getType());
            //Give human a card
            //playerHand.add(cardArrayList.get(0));

            dealCard(cardArrayList, playerHand, deck, h1.getType());
            System.out.println(playerHand.toString());
        }

        else if(e.getActionCommand().equals("Stand"))
        {
            System.out.println("Player Stand");
            surrenderButton.setEnabled(false);
        }

        else if(e.getActionCommand().equals("Surrender"))
        {
            System.out.println("Player Surrender");
        }
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
        h1.setName(name);
        int numberOfDecks = Integer.parseInt(JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))"));
        deck = new Deck(numberOfDecks);

        message.setText("Dealer");
        message.setHorizontalAlignment((JLabel.CENTER));
        message.setVerticalAlignment(((JLabel.CENTER)));
        t1.add(message);

        repaint();

        message = new JLabel();
        message.setFont(new Font("SansSerif",Font.PLAIN,30));
        message.setText(h1.getName());
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment((JLabel.CENTER));
        b1.add(message);

        repaint();

        blackjack = new Blackjack(numberOfDecks);

        return deck;
    }

    private void displayHands(Deck d)
    {
        System.out.println(d.toString());

        cardArrayList = d.getCards();
        playerHand = new ArrayList<>();

        String playerType, cardName;

        for(int i = 0; i < 4; i++)
        {
            if(i == 0 || i == 1)
            {
                playerType = "human";
                dealCard(cardArrayList, playerHand, d, playerType);
            }

            else if(i == 2)
            {
                playerType = "dealer";
                dealCard(cardArrayList, playerHand, d, playerType);
            }

            else if(i == 3)
            {
                playerType = "dealer";
                cardName = "back";
                dealCard(cardArrayList, playerHand, d, playerType, cardName);
            }

        }

        System.out.println(playerHand.toString());

    }

    private void addButtons()
    {
        JButton button;

        button = new JButton("Hit");
        button.setVerticalAlignment(JButton.CENTER);
        button.setVisible(true);
        button.addActionListener(this);
        b3r1.add(button);

        button = new JButton("Stand");
        button.setVerticalAlignment(JButton.CENTER);
        button.setVisible(true);
        button.addActionListener(this);
        b3r2.add(button);

        surrenderButton = new JButton("Surrender");
        surrenderButton.setVerticalAlignment(JButton.CENTER);
        surrenderButton.setVisible(true);
        surrenderButton.addActionListener(this);
        b3r3.add(surrenderButton);
    }

    private void dealCard(ArrayList<Card> cardArrayList, ArrayList<Card> playerHand, Deck d, String playerType)
    {
        String cardName = cardArrayList.get(0).getValue() + cardArrayList.get(0).getSuit();

        image = new ImageIcon("resources/" + cardName + ".png");
        imageLabel = new JLabel(image);
        imageLabel.setVisible(true);
        imageLabel.setHorizontalAlignment(JLabel.LEFT );

        if(playerType.equals("human"))
        {
            b2.add(imageLabel);
            playerHand.add(cardArrayList.get(0));
        }

        else if(playerType.equals("dealer"))
        {
            t2.add(imageLabel);
        }
        d.removeCard();
    }

    private void dealCard(ArrayList<Card> cardArrayList, ArrayList<Card> playerHand, Deck d, String playerType, String cardName1)
    {
        String cardName = cardName1;

        image = new ImageIcon("resources/" + cardName + ".png");
        imageLabel = new JLabel(image);
        imageLabel.setVisible(true);
        imageLabel.setHorizontalAlignment(JLabel.LEFT );
        t2.add(imageLabel);
        d.removeCard();
    }

}
