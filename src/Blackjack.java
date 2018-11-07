public class Blackjack {

    private int numberOfDecks;

    public int getNumberOfDecks()
    {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int numberOfDecks)
    {
        this.numberOfDecks = numberOfDecks;
    }

    public Blackjack(int numOfDecks)
    {
        setNumberOfDecks(numOfDecks);

        Deck deck = new Deck(getNumberOfDecks());

        //System.out.println(deck.toString());

        deck.shuffle();

        //System.out.println(deck.toString());
    }
}
