//import com.sun.deploy.util.ArrayUtil;

import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<Card>();
    private Card c;
    private int availableCards;
    private int currentAmountOfCards;
    private boolean shuffled;

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public int getAvailableCards()
    {
        return availableCards;
    }

    public int getCurrentAmountOfCards()
    {
        return currentAmountOfCards;
    }

    public boolean isShuffled()
    {
        return shuffled;
    }

    public Card getC()
    {
        return c;
    }

    public void setAvailableCards(int availableCards)
    {
        this.availableCards = availableCards;
    }

    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    public void setCurrentAmountOfCards(int currentAmountOfCards)
    {
        this.currentAmountOfCards = currentAmountOfCards;
    }

    public void setShuffled(boolean shuffled)
    {
        this.shuffled = shuffled;
    }

    public void setC(Card c)
    {
        this.c = c;
    }

    public Deck(int numberOfDecks)
    {
        setAvailableCards(52 * numberOfDecks);
        setCurrentAmountOfCards(0);
        setShuffled(false);
        //cards = new Card[getAvailableCards()];

        for(int x = 0; x < numberOfDecks; x++)
        {
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 13; j++)
                {
                    //cards[currentAmountOfCards++] = new Card(i,j);
                    cards.add(currentAmountOfCards,new Card(i,j));
                    //System.out.println(cards[currentAmountOfCards].getValue() + " " + cards[currentAmountOfCards].getSuit());
                }
            }
        }
    }

    public String toString()
    {
        String output = "";

        for(int k = 0; k < cards.size(); k++)
        {
            output += cards.get(k).getValue() + " of " + cards.get(k).getSuit() + "\n";
        }

        return output;
    }

    public void shuffle()
    {
        Random rnd = new Random();
        for(int i = 0; i < cards.size(); i++)
        {
            int j = rnd.nextInt(i+1);

            Card c = cards.get(j);//pick random card from cards array
            cards.set(j,cards.get(i)); //= cards.get(i);//
            cards.set(i,c);
        }

        setShuffled(true);
    }

    public void removeCard()
    {
        cards.remove(0);
    }

}
