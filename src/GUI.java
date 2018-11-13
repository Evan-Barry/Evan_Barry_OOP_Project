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
    JButton hitButton, standButton, surrenderButton;
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
                cardName = "back";
                dealCard(d, cardName);
            }
        }

        System.out.println(playerHand.toString());
    }

    private void addButtons()
    {
        hitButton = new JButton("Hit");
        hitButton.setVerticalAlignment(JButton.CENTER);
        hitButton.setVisible(true);
        hitButton.addActionListener(this);
        b3r1.add(hitButton);

        standButton = new JButton("Stand");
        standButton.setVerticalAlignment(JButton.CENTER);
        standButton.setVisible(true);
        standButton.addActionListener(this);
        b3r2.add(standButton);

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
            checkTotal(playerHand);
        }

        else if(playerType.equals("dealer"))
        {
            t2.add(imageLabel);
        }
        d.removeCard();

        revalidate();
        //repaint();
    }

    private void dealCard(Deck d, String cardName1)
    {

        image = new ImageIcon("resources/" + cardName1 + ".png");
        imageLabel = new JLabel(image);
        imageLabel.setVisible(true);
        imageLabel.setHorizontalAlignment(JLabel.LEFT );
        t2.add(imageLabel);
        d.removeCard();
    }

    private void checkTotal(ArrayList<Card> playerHand)
    {

        int total = 0;

        for(int i = 0; i < playerHand.size(); i++)
        {
            //System.out.println(playerHand.get(i).getValue());
            int value;
            boolean soft = false;
            boolean aceIs1 = false;
            boolean aceDrawn = false;

            switch(playerHand.get(i).getValue())
            {
                case "Ace":
                    if(aceIs1 == true)
                    {
                        value = 1;
                    }

                    else
                    {
                        value = 11;
                    }

                    soft = true;
                    aceDrawn = true;
                    break;
                case "Two":
                    value = 2;
                    break;
                case "Three":
                    value = 3;
                    break;
                case "Four":
                    value = 4;
                    break;
                case "Five":
                    value = 5;
                    break;
                case "Six":
                    value = 6;
                    break;
                case "Seven":
                    value = 7;
                    break;
                case "Eight":
                    value = 8;
                    break;
                case "Nine":
                    value = 9;
                    break;
                case "Ten":
                    value = 10;
                    break;
                case "Jack":
                    value = 10;
                    break;
                case "Queen":
                    value = 10;
                    break;
                case "King":
                    value = 10;
                    break;
                default:
                    value = 0;
            }

            total += value;

            System.out.println(total + " hello");

            if(total > 21)
            {
                bust(h1.getType());
            }

            //Fix total calculation to include if an ace is 1 or 11

        }
    }

    private void bust(String type)
    {
        JOptionPane.showMessageDialog(null, h1.getName() + " has bust!");
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        surrenderButton.setEnabled(false);
    }

}
