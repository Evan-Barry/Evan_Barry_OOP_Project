import java.util.Random;
import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();// New array list of cards, the deck
    private boolean shuffled;

    public ArrayList<Card> getCards()
    {
        return cards;
    }


    public boolean isShuffled()
    {
        return shuffled;
    }


    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    public void setShuffled(boolean shuffled)
    {
        this.shuffled = shuffled;
    }

    public Deck(int numberOfDecks)
    {
        setShuffled(false);

        for(int x = 0; x < numberOfDecks; x++)
        {
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 13; j++)
                {
                    cards.add(0,new Card(i,j));
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
