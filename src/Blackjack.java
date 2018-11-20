import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Blackjack implements EndGameConditions{

    private int numberOfDecks;

    private boolean gameOver;

    private int[] gameStats = {0,0,0,0};

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

    public Blackjack(int numOfDecks)
    {
        setNumberOfDecks(numOfDecks);

        Deck deck = new Deck(getNumberOfDecks());

        deck.shuffle();

        try {
            loadStatsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        int[] tempGameStats = getGameStats();
        tempGameStats[0]++;//add 1 game played
        if(winner.getType().equals("human"))
        {
            tempGameStats[1]++;//add 1 win
        }
        else
        {
            tempGameStats[2]++;//add 1 loss
        }

        setGameStats(tempGameStats);//update gameStats with new data

        try
        {
            saveStatsToFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void winner(Player winner)
    {
        JOptionPane.showMessageDialog(null, winner.getName() + " wins with a total of: " + winner.getHandValue(), winner.getName() + " Wins!", JOptionPane.INFORMATION_MESSAGE);
        int[] tempGameStats = getGameStats();
        tempGameStats[0]++;//add 1 game played
        if(winner.getType().equals("human"))
        {
            tempGameStats[1]++;//add 1 win
        }
        else
        {
            tempGameStats[2]++;//add 1 loss
        }

        setGameStats(tempGameStats);//update gameStats with new data

        try
        {
            saveStatsToFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw()
    {
        JOptionPane.showMessageDialog(null, "Draw");
        int[] tempGameStats = getGameStats();
        tempGameStats[0]++;//add 1 game played
        tempGameStats[3]++;//add 1 draw

        setGameStats(tempGameStats);//update gameStats with new data

        try
        {
            saveStatsToFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveStatsToFile() throws IOException
    {
        File outFile = new File("resources/stats.txt");
        FileOutputStream outFileStream = new FileOutputStream(outFile);
        PrintWriter outStream = new PrintWriter(outFileStream);

        for(int i = 0; i < gameStats.length; i++)
        {
            outStream.println(gameStats[i]);
        }

        outStream.close();
    }

    public void loadStatsFromFile() throws IOException
    {
        File inFile = new File("resources/stats.txt");
        FileReader fileReader = new FileReader(inFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        int j;
        int[] tempGameStats = new int[4];
        for(int i = 0; i < gameStats.length; i++)
        {
            str = bufferedReader.readLine();
            j = Integer.parseInt(str);
            tempGameStats[i] = j;
        }
        bufferedReader.close();

        setGameStats(tempGameStats);

    }

    public static void main(String[] args) {
        GUI window = new GUI();
        window.setVisible(true);
    }

}
