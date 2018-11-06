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
    }
}
