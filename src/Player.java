public class Player {

    private Hand hand;

    private boolean winner;

    private String type;

    public Hand getHand()
    {
        return hand;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public boolean isWinner()
    {
        return winner;
    }

    public void setWinner(boolean winner)
    {
        this.winner = winner;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void hit()
    {

    }

    public void stand()
    {

    }
}
