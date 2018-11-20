import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Blackjack implements EndGameConditions{

    private int numberOfDecks;

    private boolean gameOver;

    private int[] gameStats = {1,2,3,4};

    /*private int numGamesPlayed;

    private int numGamesWon;

    private int numGamesLost;

    private int numGamesDrawn;*/

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

    /*public int getNumGamesPlayed()
    {
        return numGamesPlayed;
    }

    public int getNumGamesWon()
    {
        return numGamesWon;
    }

    public int getNumGamesLost()
    {
        return numGamesLost;
    }

    public int getNumGamesDrawn()
    {
        return numGamesDrawn;
    }

    public void setNumGamesPlayed(int numGamesPlayed)
    {
        this.numGamesPlayed = numGamesPlayed;
    }

    public void setNumGamesWon(int numGamesWon)
    {
        this.numGamesWon = numGamesWon;
    }

    public void setNumGamesLost(int numGamesLost)
    {
        this.numGamesLost = numGamesLost;
    }

    public void setNumGamesDrawn(int numGamesDrawn)
    {
        this.numGamesDrawn = numGamesDrawn;
    }*/

    public Blackjack(int numOfDecks)
    {
        setNumberOfDecks(numOfDecks);

        Deck deck = new Deck(getNumberOfDecks());

        deck.shuffle();
    }

    public int checkTotal(ArrayList<Card> playerHand, String type)
    {
        int total = 0;

        for(int i = 0; i < playerHand.size(); i++)
        {
            int value;

            switch(playerHand.get(i).getValue())
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

        return total;
    }

    public void bust(Player loser, Player winner)
    {
        JOptionPane.showMessageDialog(null, loser.getName() + " has bust!", winner.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void winner(Player winner)
    {
        JOptionPane.showMessageDialog(null, winner.getName() + " wins with a total of: " + winner.getHandValue(), winner.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void draw()
    {
        JOptionPane.showMessageDialog(null, "Draw");
    }

    public void saveStatsToFile() throws IOException
    {
        File outFile = new File("stats.txt");
        FileOutputStream outFileStream = new FileOutputStream(outFile);
        PrintWriter outStream = new PrintWriter(outFileStream);

        for(int i = 0; i < gameStats.length; i++)
        {
            outStream.println(gameStats[i]);
        }

        outStream.close();
    }

    public static void main(String[] args) {
        GUI window = new GUI();
        window.setVisible(true);
    }

}
