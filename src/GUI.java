import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
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

    //.wav files found on https://opengameart.org/content/54-casino-sound-effects-cards-dice-chips
    static MediaPlayer mediaPlayer;
    String dealCardWav = "resources/cardDeal1.wav";
    String fanCardWav = "resources/cardFan1.wav";


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

        JFXPanel jfxPanel = new JFXPanel();
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

            //https://docs.oracle.com/javase/7/docs/api/java/awt/Component.html#revalidate()
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
            playAudio(dealCardWav);
            human.setMovesMade(human.getMovesMade()+1);//Increment the amount of moves the human has made

            //If the human player is bust
            if(human.getHandValue() > 21)
            {
                checkWinner();//Call the checkWinner method
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
                repaint();//https://docs.oracle.com/javase/7/docs/api/java/awt/Component.html#repaint()

                //Creating a string that holds the file name of the card. The card file name is made of the card
                String cardName = dealer.getHand().get(1).getValue() + dealer.getHand().get(1).getSuit();

                //Creating the image object with the file pathname
                image = new ImageIcon("resources/" + cardName + ".png");
                //Assign the image to a imageLabel
                dealerSecondCardImageLabel = new JLabel(image);
                dealerSecondCardImageLabel.setVisible(true);
                dealerSecondCardImageLabel.setHorizontalAlignment(JLabel.LEFT );

                //Displaying the second card image on the middle top panel
                t2.add(dealerSecondCardImageLabel);

                //Update the JPanel's to show changes in its elements
                revalidate();
                repaint();

                //Set the dealer's second card to face up
                setDealerSecondCardFaceUp(true);
            }

            //The dealer will hit when its total is less than 17
            while(blackjack.checkTotal(dealer.getHand(), dealer.getType()) < 17)
            {
                dealCard(cardArrayList, deck, dealer.getType());//Call the method to deal a new card to the dealer
                playAudio(dealCardWav);
                dealer.setMovesMade(dealer.getMovesMade()+1);//Increment the amount of moves made by the dealer
                revalidate();//Update the JPanel's to show changes in its elements
            }

            //The dealer will stand if its total is greater or equal to 17
            if(blackjack.checkTotal(dealer.getHand(), dealer.getType()) >= 17)
            {
                dealer.setHandValue(blackjack.checkTotal(dealer.getHand(), dealer.getType()));//Setting the hand value of the deal'er hand to the sum of the cards the dealer has
                dealer.setMovesMade(dealer.getMovesMade()+1);//Increment the amount of moves made by the dealer
                revalidate();//Update the JPanel's to show changes in its elements
                checkWinner();//Call the checkWinner method
            }
        }

        //End game if the player clicks the "Surrender" button
        else if(e.getActionCommand().equals("Surrender"))
        {
            //Display a message saying that the game is over
            JOptionPane.showMessageDialog(null, human.getName() + " Surrenders! Game Over!", dealer.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);

            //Disable the buttons
            surrenderButton.setEnabled(false);
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
        }
    }


    private void createGameMenu()
    {
        JMenuItem item;//Creating a reference for a JMenuItem

        //Creating a new JMenu called "Game" and assigning it the mnemonic G
        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        //Creating a new JMenuItem called "New Game" and assigning it the mnemonic N
        item = new JMenuItem("New Game");
        item.setMnemonic(KeyEvent.VK_N);
        item.addActionListener(this);//Assign the JMenuItem an actionListener
        gameMenu.add(item);//Add this JMenuItem to the gameMenu JMenu

        statsItem = new JMenuItem("Stats");//Creating a new JMenuItem called "Stats" and assigning it the mnemonic S
        statsItem.setMnemonic(KeyEvent.VK_S);
        statsItem.addActionListener(this);//Assign the JMenuItem an actionListener
        statsItem.setEnabled(false);//The stats menu item will be disable at the start of the program
        gameMenu.add(statsItem);//Add this JMenuItem to the gameMenu JMenu

        gameMenu.addSeparator();//Add a line to the JMenu

        item = new JMenuItem("Quit");//Creating a new JMenuItem called "Stats" and assigning it the mnemonic Q
        item.setMnemonic(KeyEvent.VK_Q);
        item.addActionListener(this);//Assign the JMenuItem an actionListener
        gameMenu.add(item);//Add this JMenuItem to the gameMenu JMenu
    }

    //The method that will set up the ga,e
    private Deck setUpGame()
    {
        //Ask the user for their name and assigning it to the human player object's name
        String name = JOptionPane.showInputDialog("What is your name?");
        human.setName(name);

        String numberOfDecksAsString;//To store the number of decks as a string from the input dialog
        int numberOfDecks = 0;//The number of decks as an integer, initialised to 0
        boolean validInput;//True if input is 1,2,3 or 4
        int i;//counter variable to cycle through input

        //Asking the user how many decks they want to play with
        numberOfDecksAsString = JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))");

        validInput = false;

        while(!validInput)
        {
            for(i = 0; i < numberOfDecksAsString.length();i++)
            {
                if(!Character.isDigit(numberOfDecksAsString.charAt(i)))
                {
                    break;
                }
            }

            if(i == numberOfDecksAsString.length())
            {
                numberOfDecks = Integer.parseInt(numberOfDecksAsString);

                if(numberOfDecks >=1 && numberOfDecks <= 4)
                {
                    validInput = true;
                }

                else
                {
                    JOptionPane.showMessageDialog(null, "Input was not between 1 and 4", "Error", JOptionPane.ERROR_MESSAGE);
                    numberOfDecksAsString = JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))");
                }
            }

            else
            {
                JOptionPane.showMessageDialog(null, "Input was not between 1 and 4", "Error", JOptionPane.ERROR_MESSAGE);
                numberOfDecksAsString = JOptionPane.showInputDialog("How many decks do you want to play with? (1(Easy) - 4(Very Hard))");
            }
        }

        playAudio(fanCardWav);

        //Creating a new deck with the amount of decks given my the user
        deck = new Deck(numberOfDecks);

        //Display the dealer player object's name in the top left panel
        message.setText(dealer.getName());
        message.setHorizontalAlignment((JLabel.CENTER));
        message.setVerticalAlignment(((JLabel.CENTER)));
        t1.add(message);

        repaint();//Update the JPanel's to show changes in its elements

        //Setting up a new instance of message JLabel
        message = new JLabel();
        message.setFont(new Font("SansSerif",Font.PLAIN,30));

        //Display the human player object's name in the bottom left panel
        message.setText(human.getName());
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment((JLabel.CENTER));
        b1.add(message);

        repaint();//Update the JPanel's to show changes in its elements

        //Setting a new instance of Blackjack and passing through how many deck to play with
        blackjack = new Blackjack(numberOfDecks);

        return deck;
    }

    //This method will display the initial 2 cards for both players
    private void displayHands(Deck d)
    {
        cardArrayList = d.getCards();//Load up the deck into an array list

        String playerType, cardName;

        for(int i = 0; i < 4; i++)//For loop that will cycle through each of the 4 cards
        {
            if(i == 0 || i == 1)//If the cards are the first 2, deal to human
            {
                playerType = "human";
                dealCard(cardArrayList, d, playerType);
            }

            else if(i == 2)//If the card is the 3rd card, deal to dealer
            {
                playerType = "dealer";
                dealCard(cardArrayList, d, playerType);
            }

            else {//deal the face down card for the dealer's second card (4th overall). The dealer will still get a 2nd card but it is displayed face down
                cardName = "back";
                dealCard(d, cardName);
            }
        }

        //If the human player gets 21 straight away from first 2 cards, call checkWinner method
        if(human.getHandValue() == 21)
        {
            checkWinner();
        }
    }

    //This method creates and displays the buttons that the human player will use to play the game
    private void addButtons()
    {
        //Create the "Hit" button, give it an actionListener and display it
        hitButton = new JButton("Hit");
        hitButton.setVerticalAlignment(JButton.CENTER);
        hitButton.setVisible(true);
        hitButton.addActionListener(this);
        b3r1.add(hitButton);

        //Create the "Stand" button, give it an actionListener and display it
        standButton = new JButton("Stand");
        standButton.setVerticalAlignment(JButton.CENTER);
        standButton.setVisible(true);
        standButton.addActionListener(this);
        b3r2.add(standButton);

        //Create the "Surreneder" button, give it an actionListener and display it
        surrenderButton = new JButton("Surrender");
        surrenderButton.setVerticalAlignment(JButton.CENTER);
        surrenderButton.setVisible(true);
        surrenderButton.addActionListener(this);
        b3r3.add(surrenderButton);

        setButtonsEnabled(true);//Indicate that the buttons are enabled
    }

    //This method will disable the buttons when needed
    private void disableButtons()
    {
        //The method will only disable the buttons if the buttons are already enabled
        if(isButtonsEnabled())
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            surrenderButton.setEnabled(false);

            setButtonsEnabled(false);//Indicate that the buttons are disabled
        }

    }

    //This method will deal a card to a player
    private void dealCard(ArrayList<Card> cardArrayList, Deck d, String playerType)
    {
        //Gets and stores the name of the card of the top of the deck
        String cardName = cardArrayList.get(0).getValue() + cardArrayList.get(0).getSuit();

        //Creates an image that will show the face of the card on top of the deck
        image = new ImageIcon("resources/" + cardName + ".png");
        JLabel imageLabel = new JLabel(image);
        imageLabel.setVisible(true);
        imageLabel.setHorizontalAlignment(JLabel.LEFT );

        //If the human player is to be dealt a card
        if(playerType.equals("human"))
        {
            b2.add(imageLabel);//Add the card image to the centre bottom panel
            human.hit(cardArrayList.get(0));//Call the human's hit method and pass through the card on top of the deck
            human.setHandValue(blackjack.checkTotal(human.getHand(), human.getType()));//Update the player's hand value with the card just dealt
        }

        //If the dealer player is to be dealt a card
        else if(playerType.equals("dealer"))
        {
            t2.add(imageLabel);//Add the card image to the centre top panel
            dealer.hit(cardArrayList.get(0));//Call the dealer's hit method and pass through the card on top of the deck
            dealer.setHandValue(blackjack.checkTotal(dealer.getHand(), dealer.getType()));//Update the player's hand value with the card just dealt
        }

        d.removeCard();//Remove the card from the top of deck
    }

    //This dealCard method will deal the face down card to the dealer
    private void dealCard(Deck d, String cardName1)
    {

        //Creates an image that will show the face of the card on top of the deck
        image = new ImageIcon("resources/" + cardName1 + ".png");
        dealerSecondCardImageLabel = new JLabel(image);
        dealerSecondCardImageLabel.setVisible(true);
        dealerSecondCardImageLabel.setHorizontalAlignment(JLabel.LEFT );
        t2.add(dealerSecondCardImageLabel);//Add the card image to the centre top panel
        dealer.hit(cardArrayList.get(0));//Call the dealer's hit method and pass through the card on top of the deck
        blackjack.checkTotal(dealer.getHand(), dealer.getType());//Check the total of the dealer's hand
        d.removeCard();//Remove the card from the top of the deck
    }

    //This method will compare the players' hands to check if a player has bust, if a player has 21 or a player has a higher value hand than the other player
    private void checkWinner()
    {
        //If the human player's hand value is 21 after the initial deal and the dealer does not have 21 as well and the game is not over
        if(human.getHandValue() == 21 && human.getMovesMade() == 0 && dealer.getHandValue() != 21 && !blackjack.isGameOver())
        {
            //Display a message saying the human wins with a Blackjack hand
            JOptionPane.showMessageDialog(null, "Blackjack! " + human.getName() + " Wins!", "Blackjack!", JOptionPane.INFORMATION_MESSAGE);
            blackjack.setGameOver(true);//Set the game to be over
        }

        //If the human has more than 21 and the game is not over
        if(human.getHandValue() > 21 && !blackjack.isGameOver())
        {
            blackjack.bust(human, dealer);//Call the bust method of Blackjack and pass the human in as the loser(player who bust) and the dealer as the winner
            blackjack.setGameOver(true);//Set the game to be over
        }

        //If the dealer has more than 21 and the game is not over
        else if(dealer.getHandValue() > 21 && !blackjack.isGameOver())
        {
            blackjack.bust(dealer, human);//Call the bust method of Blackjack and pass the dealer in as the loser(player who bust) and the human as the winner
            blackjack.setGameOver(true);//Set the game to be over
        }

        //if the human's hand value is greater than the dealer's hand value and the game is not over
        else if(human.getHandValue() > dealer.getHandValue() && !blackjack.isGameOver())
        {
            blackjack.winner(human);//Call the winner method of Blackjack and pass the human in as the winner
            blackjack.setGameOver(true);//Set the game to be over
        }

        //if the dealer's hand value is greater than the human's hand value and the game is not over
        else if(dealer.getHandValue() > human.getHandValue() && !blackjack.isGameOver())
        {
            blackjack.winner(dealer);//Call the winner method of Blackjack and pass the dealer in as the winner
            blackjack.setGameOver(true);//Set the game to be over
        }

        //If the human and the dealer have the same hand value and the game is not over
        else if(human.getHandValue() == dealer.getHandValue() && !blackjack.isGameOver())
        {
            blackjack.draw();//Call the draw method of Blackjack
            blackjack.setGameOver(true);//Set the game to be over
        }

        //If the game is over
        if(blackjack.isGameOver())
        {
            disableButtons();//Disable the buttons
        }

    }

    //This method will clear all the panels of their elements and update the panels
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

    public static void playAudio(String path)
    {
        Media audioClip = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(audioClip);

        try
        {
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println("The audio file " + path + " could not be played!");
        }
    }

}
