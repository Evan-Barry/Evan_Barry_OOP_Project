import java.util.Random;
import java.util.ArrayList;

/**
 * Represents a deck of cards
 */
public class Deck {

    /**
     * The array holding the card, the deck
     */
    private ArrayList<Card> cards = new ArrayList<>();// New array list of cards, the deck

    /**
     * Indicates if the deck is shuffled
     */
    private boolean shuffled = false;

    /**
     * Gets the array list of cards
     * @return the deck of cards
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    /**
     * Gets the shuffled state of the deck
     * @return the shuffled status
     */
    public boolean isShuffled()
    {
        return shuffled;
    }

    /**
     * Changes the cards of the deck
     * @param cards
     */
    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    /**
     * Changes the shuffles state of the deck
     * @param shuffled
     */
    public void setShuffled(boolean shuffled)
    {
        this.shuffled = shuffled;
    }

    /**
     * Creates a new deck with the given number of decks the new deck will be comprised of
     * @param numberOfDecks
     */
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

    /**
     * Lists out all the cards in the deck
     * @return An output of all the cards
     */
    public String toString()
    {
        String output = "";

        for(int k = 0; k < cards.size(); k++)
        {
            output += cards.get(k).getValue() + " of " + cards.get(k).getSuit() + "\n";
        }

        return output;
    }

    /**
     * Takes the deck and shuffles it and sets the shuffles state of the deck to true
     */
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

    /**
     * Removes the top card of the deck. Top card is card at element 0
     */
    public void removeCard()
    {
        cards.remove(0);
    }

}
