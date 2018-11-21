import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{

    //Declaring JFrame elements
    private JMenu gameMenu;
    private JLabel message;
    private JPanel t1;
    private JPanel t2;
    private JPanel t3;
    private JPanel b1;
    private JPanel b2;
    private JPanel b3;
    private JPanel b3r1, b3r2, b3r3;
    private JLabel dealerSecondCardImageLabel;
    private static JButton hitButton, standButton, surrenderButton;
    private ImageIcon image;
    private JMenuItem statsItem;

    //Creating a reference for the deck and cardArrayList
    private Deck deck;
    private ArrayList<Card> cardArrayList;

    //Creating the two player objects
    private Player human = new Player("Unknown", "human");
    private Player dealer = new Player("Casino", "dealer");

    //Creating a reference for the blackjack object
    private Blackjack blackjack;

    //Setting some variables for the start of the game
    private boolean dealerSecondCardFaceUp = false;
    private boolean buttonsEnabled = false;
    private boolean gameRunning = false;

    GUI()
    {
        //Setting up basic JFrame settings
        setTitle("Blackjack");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());//The JFrame will be the size of the screen
        setResizable(false);

        //Setting up the layout of the JFrame. GridLayout allows for easy placing of elements
        Container cPane = getContentPane();
        cPane.setLayout(new GridLayout(2,1));
        cPane.setBackground(new Color(39,119,20));

        //Setting up 2 JPanels to divide my frame into 2 parts. topPanel will show the dealer's hand. bottomPanel will show the player's hand and options
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //The size of the panels will be the width of the screen and half the height of the screen
        topPanel.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight()/2);
        bottomPanel.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight()/2);

        topPanel.setLocation(0,0);//The origin location of the topPanel will be 0,0 meaning the top left corner of the JPanel will be in the top left corner of the JFrame
        bottomPanel.setLocation(0, (int) screenSize.getHeight()/2);//The origin location of the bottom panel will half way down the left side of the JFrame

        //Adding the 2 panels to the JFrame
        cPane.add(topPanel);
        cPane.add(bottomPanel);

        //Dividing up each panel into 3 columns
        topPanel.setLayout(new GridLayout(1,3));
        bottomPanel.setLayout(new GridLayout(1,3));

        //Setting up 3 new JPanels, these will be the 3 columns of the top panel
        t1 = new JPanel();
        t2 = new JPanel();
        t3 = new JPanel();

        //Setting the colour of these panels to color of the JFrame
        t1.setBackground(cPane.getBackground());
        t2.setBackground(cPane.getBackground());
        t3.setBackground(cPane.getBackground());

        //Adding the 3 column panels to the top panel
        topPanel.add(t1);
        topPanel.add(t2);
        topPanel.add(t3);

        //Setting up 3 new JPanels, these will be the 3 columns of the bottom panel
        b1 = new JPanel();
        b2 = new JPanel();
        b3 = new JPanel();

        //Setting the colour of these panels to color of the JFrame
        b1.setBackground(cPane.getBackground());
        b2.setBackground(cPane.getBackground());
        b3.setBackground(cPane.getBackground());

        //Adding the 3 column panels to the bottom panel
        bottomPanel.add(b1);
        bottomPanel.add(b2);
        bottomPanel.add(b3);

        //Dividing up the right column of the bottom panel into 3 rows. Each row will house an action button
        b3.setLayout(new GridLayout(3,1));

        //Setting up 3 new JPanels, these will be the 3 rows of the bottom right panel
        b3r1 = new JPanel();
        b3r2 = new JPanel();
        b3r3 = new JPanel();

        //Setting the colour of these panels to color of the JFrame
        b3r1.setBackground(cPane.getBackground());
        b3r2.setBackground(cPane.getBackground());
        b3r3.setBackground(cPane.getBackground());

        //Adding the 3 row panels to the bottom right panel
        b3.add(b3r1);
        b3.add(b3r2);
        b3.add(b3r3);

        //Calling the method to create the game menu
        createGameMenu();

        //Setting up a new menuBar
        JMenuBar menuBar = new JMenuBar();
        //Setting this new menuBar to be the menu bar
        setJMenuBar(menuBar);
        //Setting the menu created in the createGameMenu() to the menuBar
        menuBar.add(gameMenu);

        //Creating and adding a welcome message
        message = new JLabel("Welcome");
        message.setFont(new Font("SansSerif",Font.PLAIN,30));
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment((JLabel.TOP));
        t2.add(message);

        //Assign the exit button on the window to close the program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //Accessor and Mutator methods for the attributes
    private boolean isDealerSecondCardFaceUp()
    {
        return dealerSecondCardFaceUp;
    }

    private void setDealerSecondCardFaceUp(boolean dealerSecondCardFaceUp)
    {
        this.dealerSecondCardFaceUp = dealerSecondCardFaceUp;
    }

    public boolean isButtonsEnabled()
    {
        return buttonsEnabled;
    }

    public void setButtonsEnabled(boolean buttonsEnabled)
    {
        this.buttonsEnabled = buttonsEnabled;
    }

    public boolean isGameRunning()
    {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning)
    {
        this.gameRunning = gameRunning;
    }

    @Override
    public void actionPerformed(ActionEvent e)//Creating the actionPerformed class that will listen for events
    {

        //Quit the program will the "Quit" menu item is clicked
        if(e.getActionCommand().equals("Quit"))
        {
            System.exit(0);
        }

        //Create a new game if the "New Game" menu item is clicked
        else if(e.getActionCommand().equals("New Game"))
        {
            //If the game is running
            if(isGameRunning())
            {
                clearPanels();//Call the method that will clear the panels of their elements

                //Resetting the player objects, therefore clearing any cards they have
                human = null;
                dealer = null;

                //Creating new player objects
                human = new Player("Unknown", "human");
                dealer = new Player("Casino", "dealer");

                //Resetting the deck
                deck = null;

                //Setting the dealer's second card to be face down
                setDealerSecondCardFaceUp(false);
            }

            deck = setUpGame();//Setting up a new deck
            deck.shuffle();//Shuffling the deck
            displayHands(deck);//This will deal the initial 2 cards to both players
            addButtons();//Add the buttons to the panels
            statsItem.setEnabled(true);//Enable the stats menu item
            setGameRunning(true);//Indicate that a game is running

            revalidate();//Update the JPanel's to show changes in its elements
        }

        //Display the stats if the "Stats" menu item is clicked
        else if(e.getActionCommand().equals("Stats"))
        {
            //Create an array to hold the stats. Loaded in values from the Blackjack class
            int[] gameStats = blackjack.getGameStats();

            //The formatted message that will display the stats
            String statsFormatted = String.format("%-20s%d\n%-20s%d\n%-20s%d\n%-20s%d","Games Played:", gameStats[0],"Games Won:", gameStats[1],"Games Lost:", gameStats[2],"Games Drawn:", gameStats[3]);

            //Display the stats on a JOptionPane
            JOptionPane.showMessageDialog(null, statsFormatted, "Stats", JOptionPane.INFORMATION_MESSAGE);
        }

        //Dealer a new card for the human player if the "Hit" button is clicked
        else if(e.getActionCommand().equals("Hit"))
        {
            //Disable the "Surrender" button because surrender option cannot be used after first move
            surrenderButton.setEnabled(false);

            //Call method that will deal the next card from the deck to the human player
            dealCard(cardArrayList, deck, human.getType());
            human.setMovesMade(human.getMovesMade()+1);//Increment the amount of moves the human has made

            //If the human player is bust
            if(human.getHandValue() > 21)
            {
                checkWinner(0);//Call the checkWinner method
                disableButtons();//Disable buttons as game is over
            }

            revalidate();//Update the JPanel's to show changes in its elements
        }

        //Make the dealer move if the "Stand" button is clicked
        else if(e.getActionCommand().equals("Stand"))
        {
            //Disable surrender and hit buttons as human player cannot make a move while dealer is making a move
            surrenderButton.setEnabled(false);
            hitButton.setEnabled(false);

            human.setMovesMade(human.getMovesMade()+1);//Increment the number of moves the human player has made

            //If the dealer's second card is face down
            if(!isDealerSecondCardFaceUp())
            {
                t2.remove(dealerSecondCardImageLabel);//Remove the image of the card face down

                //Update the JPanel's to show changes in its elements
                revalidate();
                repaint();

                //Creating a string that holds the name of the card. The card name is made of the card
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
                dealer.setMovesMade(dealer.getMovesMade()+1);
                revalidate();
                checkWinner(11);
            }

            if(blackjack.checkTotal(dealer.getHand(), dealer.getType()) >= 17)
            {
                dealer.setHandValue(blackjack.checkTotal(dealer.getHand(), dealer.getType()));
                dealer.setMovesMade(dealer.getMovesMade()+1);
                revalidate();
                //setEnabled(true);
                checkWinner(12);
            }
        }

        else if(e.getActionCommand().equals("Surrender"))
        {
            JOptionPane.showMessageDialog(null, human.getName() + " Surrenders! Game Over!", dealer.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);
            surrenderButton.setEnabled(false);
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
        }
    }


    private void createGameMenu()
    {
        JMenuItem item;

        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        item = new JMenuItem("New Game");
        item.setMnemonic(KeyEvent.VK_N);
        item.addActionListener(this);
        gameMenu.add(item);

        statsItem = new JMenuItem("Stats");
        statsItem.setMnemonic(KeyEvent.VK_S);
        statsItem.addActionListener(this);
        statsItem.setEnabled(false);
        gameMenu.add(statsItem);

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

        while(numberOfDecks < 1 || numberOfDecks > 4)
        {
            JOptionPane.showMessageDialog(null, "Input was not between 1 and 4", "Error", JOptionPane.ERROR_MESSAGE);
            numberOfDecks = Integer.parseInt(JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))"));
        }


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
        //System.out.println(d.toString());

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

        setButtonsEnabled(true);
    }

    private void disableButtons()
    {
        if(isButtonsEnabled())
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            surrenderButton.setEnabled(false);

            setButtonsEnabled(false);
        }

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

    public void clearPanels()
    {
        t1.removeAll();
        t1.updateUI();

        t2.removeAll();
        t2.updateUI();

        t3.removeAll();
        t3.updateUI();

        b1.removeAll();
        b1.updateUI();

        b2.removeAll();
        b2.updateUI();

        b3r1.removeAll();
        b3r1.updateUI();

        b3r2.removeAll();
        b3r2.updateUI();

        b3r3.removeAll();
        b3r3.updateUI();
    }

}
