import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Blackjack implements EndGameConditions{

    //Declaring the attributes for the class
    private int numberOfDecks;//Blackjack can have multiple decks for increased difficulty

    private boolean gameOver;

    private int[] gameStats = {0,0,0,0};//This array tracks the amount of games played, won, lost and drawn
    //This array is loaded up from a file when a game starts and saves to the file when a game is finished
    //Element 0 tracks games played, Element 1 tracks games won, Element 2 tracks games lost and Element 3 tracks games drawn

    //Accessor and mutator methods for the attributes
    public int getNumberOfDecks()
    {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int numberOfDecks)
    {
        this.numberOfDecks = numberOfDecks;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public int[] getGameStats()
    {
        return gameStats;
    }

    public void setGameStats(int[] gameStats)
    {
        this.gameStats = gameStats;
    }

    public Blackjack(int numOfDecks)//Single argument Constructor
    {
        setNumberOfDecks(numOfDecks);//numOfDecks is passed in from the user when the user is asked how many decks they would like

        Deck deck = new Deck(getNumberOfDecks());//Setting up a new deck of cards with the specified amount of decks

        deck.shuffle();//Shuffling the deck

        try {
            loadStatsFromFile();//Load in the game stats from a file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int checkTotal(ArrayList<Card> playerHand, String type)//Player's hand are passed in here and the hand total is calculated
    {
        int total = 0;

        for(int i = 0; i < playerHand.size(); i++)//This for loop cycles through all cards in a players hand
        {
            int value;

            switch(playerHand.get(i).getValue())//At the end of the for loop, the Value variable is set to the sum of all cards in a players hand
            {
                case "Ace":
                    value = 11;
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

            System.out.println(total + " " + type + " i count : " + i);
        }

        return total;//The total is returned
    }

    public void bust(Player loser, Player winner)//This method is called when a player busts(goes over 21). bust() is a method from the EndGameConditions interface
    {
        JOptionPane.showMessageDialog(null, loser.getName() + " has bust!", winner.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);//Message informing of who is the winner
        int[] tempGameStats = getGameStats();//The tempGameStats is loaded with the array gameStats
        tempGameStats[0]++;//add 1 game played
        if(winner.getType().equals("human"))//If the winner is the human player
        {
            tempGameStats[1]++;//add 1 win
        }
        else
        {
            tempGameStats[2]++;//add 1 loss
        }

        setGameStats(tempGameStats);//update gameStats with new data from tempGameStats

        try
        {
            saveStatsToFile();//Save the gameStats array to the file
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void winner(Player winner)//This method is called when a player wins by having the better hand. winner() is a method from the EndGameConditions interface
    {
        JOptionPane.showMessageDialog(null, winner.getName() + " wins with a total of: " + winner.getHandValue(), winner.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);//Message informing of who is the winner
        int[] tempGameStats = getGameStats();//The tempGameStats array is loaded with the array gameStats
        tempGameStats[0]++;//add 1 game played
        if(winner.getType().equals("human"))//If the winner is the human player
        {
            tempGameStats[1]++;//add 1 win
        }
        else
        {
            tempGameStats[2]++;//add 1 loss
        }

        setGameStats(tempGameStats);//update gameStats with new data from tempGameStats

        try
        {
            saveStatsToFile();//Save the gameStats array to the file
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw()//This method is called when there is a draw(both player stand with same hand value). draw() is a method from the EndGameConditions interface
    {
        JOptionPane.showMessageDialog(null, "Draw");//Message informing of the game ending in a draw
        int[] tempGameStats = getGameStats();//The tempGameStats array is loaded with the array gameStats
        tempGameStats[0]++;//add 1 game played
        tempGameStats[3]++;//add 1 draw

        setGameStats(tempGameStats);//update gameStats with new data from tempGameStats

        try
        {
            saveStatsToFile();//Save gameStats array to the file
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveStatsToFile() throws IOException//This method will save the gameStats array to a file
    {
        File outFile = new File("resources/stats.txt");//File to save to
        FileWriter outFileWriter = new FileWriter(outFile);//FileWriter will make it possible to write streams of characters to a file
        PrintWriter outStream = new PrintWriter(outFileWriter);//The PrintWriter class will write formatted data to a file

        for(int i = 0; i < gameStats.length; i++)//For loop to cycle through all elements of gameStats array
        {
            outStream.println(gameStats[i]);//Write this element of gameStats to the file
        }

        outStream.close();//Close the stream
    }

    public void loadStatsFromFile() throws IOException
    {
        File inFile = new File("resources/stats.txt");//The File to load in from
        FileReader fileReader = new FileReader(inFile);//FileReader will make it possible to read contents from a file
        BufferedReader bufferedReader = new BufferedReader(fileReader);//BufferedReader will read data from a character stream, fileReader
        String str;//Declaring a string to store each line a of data from the file
        int j;//Declaring an int to store a number that will be parsed from a string
        int[] tempGameStats = new int[4];//Declaring a temp array to store game stats
        for(int i = 0; i < gameStats.length; i++)//For loop to cycle through the main gameStats array
        {
            str = bufferedReader.readLine();//Store the current line from the file in the String str
            j = Integer.parseInt(str);//Parse the String str to the Int j
            tempGameStats[i] = j;//Set the current index of tempGameStats to the value of j
        }
        bufferedReader.close();//Close the buffered reader

        setGameStats(tempGameStats);//Set the gameStats array to the value of the tempGameStats array

    }

    public static void main(String[] args) {
        GUI window = new GUI();//Create a new instance of GUI
        window.setVisible(true);//Set it to be visible
    }

}
