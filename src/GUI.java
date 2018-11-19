import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{

    private JMenu gameMenu;
    private JLabel message;
    private JPanel t1;
    private JPanel t2;
    private JPanel b1;
    private JPanel b2;
    private JPanel b3r1, b3r2, b3r3;
    private JLabel dealerSecondCardImageLabel;
    private static JButton hitButton, standButton, surrenderButton;
    private ImageIcon image;

    private Deck deck;
    private ArrayList<Card> cardArrayList;

    private Player human = new Player("Unknown", "human");
    private Player dealer = new Player("Casino", "dealer");
    private Blackjack blackjack;
    private boolean dealerSecondCardFaceUp = false;

    GUI()
    {
        setTitle("Blackjack");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        setResizable(false);

        Container cPane = getContentPane();
        cPane.setLayout(new GridLayout(2,1));
        cPane.setBackground(new Color(39,119,20));

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight()/2);
        bottomPanel.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight()/2);

        topPanel.setLocation(0,0);
        bottomPanel.setLocation(0, (int) screenSize.getHeight()/2);

        cPane.add(topPanel);
        cPane.add(bottomPanel);

        topPanel.setLayout(new GridLayout(1,3));
        bottomPanel.setLayout(new GridLayout(1,3));

        t1 = new JPanel();
        t2 = new JPanel();
        JPanel t3 = new JPanel();

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
        JPanel b3 = new JPanel();

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private boolean isDealerSecondCardFaceUp()
    {
        return dealerSecondCardFaceUp;
    }

    private void setDealerSecondCardFaceUp(boolean dealerSecondCardFaceUp)
    {
        this.dealerSecondCardFaceUp = dealerSecondCardFaceUp;
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
            deck = setUpGame();
            deck.shuffle();
            //displayHands(deck);
            displayHands(deck);
            addButtons();
        }

        else if(e.getActionCommand().equals("Hit"))
        {
            System.out.println("Player Hit");
            surrenderButton.setEnabled(false);

            dealCard(cardArrayList, deck, human.getType());
            human.setLastMoveMade("HIT");
            human.setMovesMade(human.getMovesMade()+1);
            System.out.println(human.getHand().toString());
            if(human.getHandValue() > 21)
            {
                checkWinner(0);
            }
        }

        else if(e.getActionCommand().equals("Stand"))
        {
            System.out.println("Player Stand");
            surrenderButton.setEnabled(false);
            hitButton.setEnabled(false);
            human.setLastMoveMade("STAND");
            human.setMovesMade(human.getMovesMade()+1);

            if(!isDealerSecondCardFaceUp())
            {
                t2.remove(dealerSecondCardImageLabel);
                revalidate();
                repaint();

                String cardName = dealer.getHand().get(1).getValue() + dealer.getHand().get(1).getSuit();

                image = new ImageIcon("resources/" + cardName + ".png");
                dealerSecondCardImageLabel = new JLabel(image);
                dealerSecondCardImageLabel.setVisible(true);
                dealerSecondCardImageLabel.setHorizontalAlignment(JLabel.LEFT );

                t2.add(dealerSecondCardImageLabel);
                revalidate();
                repaint();

                setDealerSecondCardFaceUp(true);
            }

            while(blackjack.checkTotal(dealer.getHand(), dealer.getType()) < 17)
            {
                dealCard(cardArrayList, deck, dealer.getType());
                dealer.setLastMoveMade("HIT");
                dealer.setMovesMade(dealer.getMovesMade()+1);
                checkWinner(11);
            }

            if(blackjack.checkTotal(dealer.getHand(), dealer.getType()) >= 17)
            {
                dealer.setLastMoveMade("STAND");
                dealer.setHandValue(blackjack.checkTotal(dealer.getHand(), dealer.getType()));
                dealer.setMovesMade(dealer.getMovesMade()+1);
                hitButton.setEnabled(true);
                checkWinner(12);
            }
        }

        else if(e.getActionCommand().equals("Surrender"))
        {
            JOptionPane.showMessageDialog(null, "Human Surrenders! Game Over!");
            surrenderButton.setEnabled(false);
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
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
        human.setName(name);
        int numberOfDecks = Integer.parseInt(JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))"));
        deck = new Deck(numberOfDecks);

        message.setText(dealer.getName());
        message.setHorizontalAlignment((JLabel.CENTER));
        message.setVerticalAlignment(((JLabel.CENTER)));
        t1.add(message);

        repaint();

        message = new JLabel();
        message.setFont(new Font("SansSerif",Font.PLAIN,30));
        message.setText(human.getName());
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

        String playerType, cardName;

        for(int i = 0; i < 4; i++)
        {
            if(i == 0 || i == 1)
            {
                playerType = "human";
                dealCard(cardArrayList, d, playerType);
            }

            else if(i == 2)
            {
                playerType = "dealer";
                dealCard(cardArrayList, d, playerType);
            }

            else {
                cardName = "back";
                dealCard(d, cardName);
            }
        }

        if(human.getHandValue() == 21)
        {
            checkWinner(2);
        }

        System.out.println("Human hand - " + human.getHand().toString());
        System.out.println("Dealer hand - " + dealer.getHand().toString());
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

    private static void disableButtons()
    {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        surrenderButton.setEnabled(false);
    }

    private void dealCard(ArrayList<Card> cardArrayList, Deck d, String playerType)
    {
        String cardName = cardArrayList.get(0).getValue() + cardArrayList.get(0).getSuit();

        image = new ImageIcon("resources/" + cardName + ".png");
        JLabel imageLabel = new JLabel(image);
        imageLabel.setVisible(true);
        imageLabel.setHorizontalAlignment(JLabel.LEFT );

        if(playerType.equals("human"))
        {
            b2.add(imageLabel);
            human.hit(cardArrayList.get(0));
            human.setHandValue(blackjack.checkTotal(human.getHand(), human.getType()));
        }

        else if(playerType.equals("dealer"))
        {
            t2.add(imageLabel);
            dealer.hit(cardArrayList.get(0));
            dealer.setHandValue(blackjack.checkTotal(dealer.getHand(), dealer.getType()));
        }
        d.removeCard();

        revalidate();
        //repaint();


    }

    private void dealCard(Deck d, String cardName1)
    {

        image = new ImageIcon("resources/" + cardName1 + ".png");
        dealerSecondCardImageLabel = new JLabel(image);
        dealerSecondCardImageLabel.setVisible(true);
        dealerSecondCardImageLabel.setHorizontalAlignment(JLabel.LEFT );
        t2.add(dealerSecondCardImageLabel);
        dealer.hit(cardArrayList.get(0));
        blackjack.checkTotal(dealer.getHand(), dealer.getType());
        d.removeCard();
    }

    private void checkWinner(int code)
    {
        if(human.getHandValue() == 21 && human.getMovesMade() == 0 && dealer.getHandValue() != 21 && !blackjack.isGameOver())
        {
            JOptionPane.showMessageDialog(null, "Blackjack! " + human.getName() + " Wins!", "Blackjack!", JOptionPane.INFORMATION_MESSAGE);
            blackjack.setGameOver(true);
        }

        System.out.println("Checking winner" + "\nhuman hand value - " + human.getHandValue()+ "\ndealer hand value - " + dealer.getHandValue());

        if(human.getHandValue() > 21 && !blackjack.isGameOver())
        {
            blackjack.bust(human, dealer);
            blackjack.setGameOver(true);
            System.out.println("Game Code - " + code);
        }

        else if(dealer.getHandValue() > 21 && !blackjack.isGameOver())
        {
            blackjack.bust(dealer, human);
            blackjack.setGameOver(true);
            System.out.println("Game Code - " + code);
        }

        else if(human.getHandValue() > dealer.getHandValue() && !blackjack.isGameOver())
        {
            blackjack.winner(human);
            blackjack.setGameOver(true);
            System.out.println("Game Code - " + code);
        }

        else if(dealer.getHandValue() > human.getHandValue() && !blackjack.isGameOver())
        {
            blackjack.winner(dealer);
            blackjack.setGameOver(true);
            System.out.println("Game Code - " + code);
        }

        else if(human.getHandValue() == dealer.getHandValue() && !blackjack.isGameOver())
        {
            blackjack.draw();
            blackjack.setGameOver(true);
            System.out.println("Game code " + code);
        }

        if(blackjack.isGameOver())
        {
            disableButtons();
        }

    }

}
