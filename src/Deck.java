public class Deck {

    private Card cards[];
    private int availableCards;
    private int currentAmountOfCards;

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

    public Deck()
    {
        setAvailableCards(52);
        setCurrentAmountOfCards(0);
        cards = new Card[getAvailableCards()];

        for(int i = 0; i < Suit.values().length; i++)
        {
            for(int j = 0; j < Value.values().length; j++)
            {
                cards[currentAmountOfCards++] = new Card(i,j);
                //System.out.println(cards[currentAmountOfCards].getValue() + " " + cards[currentAmountOfCards].getSuit());
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

}
