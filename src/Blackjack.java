import javax.swing.*;
import java.util.ArrayList;

public class Blackjack {

    private int numberOfDecks;

    private boolean gameOver;

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

    public Blackjack(int numOfDecks)
    {
        setNumberOfDecks(numOfDecks);

        Deck deck = new Deck(getNumberOfDecks());

        //System.out.println(deck.toString());

        deck.shuffle();

        //System.out.println(deck.toString());
    }

    public int checkTotal(ArrayList<Card> playerHand, String type)
    {
        int total = 0;

        for(int i = 0; i < playerHand.size(); i++)
        {
            //System.out.println(playerHand.get(i).getValue());
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

            if(total == 21)
            {
                winner(type);
            }

            if(total > 21)
            {
                bust(type);
            }
        }

        return total;
    }

    private void bust(String type)
    {
        JOptionPane.showMessageDialog(null, type + " has bust!");
        GUI.disableButtons();
    }

    private void winner(String type)
    {
        JOptionPane.showMessageDialog(null, type + " wins!");
    }

}
