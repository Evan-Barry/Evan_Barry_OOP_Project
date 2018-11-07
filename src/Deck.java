import java.util.Random;

public class Deck {

    private Card[] cards;
    private Card c;
    private int availableCards;
    private int currentAmountOfCards;
    private boolean shuffled;

    public Card[] getCards()
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

    public Card getC(int i)
    {
        c = cards[i];

        return c;
    }

    public void setAvailableCards(int availableCards)
    {
        this.availableCards = availableCards;
    }

    public void setCards(Card[] cards)
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
        cards = new Card[getAvailableCards()];

        for(int x = 0; x < numberOfDecks; x++)
        {
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 13; j++)
                {
                    cards[currentAmountOfCards++] = new Card(i,j);
                    //System.out.println(cards[currentAmountOfCards].getValue() + " " + cards[currentAmountOfCards].getSuit());
                }
            }
        }
    }

    public String toString()
    {
        String output = "";

        for(int k = 0; k < cards.length; k++)
        {
            output += cards[k].getValue() + " of " + cards[k].getSuit() + "\n";
        }

        return output;
    }

    public void shuffle()
    {
        Random rnd = new Random();
        for(int i = 0; i < cards.length; i++)
        {
            int j = rnd.nextInt(i+1);

            Card c = cards[j];//pick random card from cards array
            cards[j] = cards[i];//
            cards[i] = c;
        }

        setShuffled(true);
    }

}
