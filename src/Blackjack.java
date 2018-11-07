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
}
