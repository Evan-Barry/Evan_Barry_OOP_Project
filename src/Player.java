import java.util.ArrayList;

public class Player {

    private String type;
    private String name;
    private ArrayList<Card> hand = new ArrayList<>();//array list of cards, the hand
    private String lastMoveMade;
    private int movesMade;
    private int handValue;

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public String getLastMoveMade()
    {
        return lastMoveMade;
    }

    public int getHandValue()
    {
        return handValue;
    }

    public int getMovesMade()
    {
        return movesMade;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setLastMoveMade(String lastMoveMade)
    {
        this.lastMoveMade = lastMoveMade;
    }

    public void setMovesMade(int movesMade)
    {
        this.movesMade = movesMade;
    }

    public void setHandValue(int handValue)
    {
        this.handValue = handValue;
    }

    public Player(String name, String type)
    {
        setName(name);
        setType(type);
        setMovesMade(0);
        setHandValue(0);
    }

    public void hit(Card c)
    {
        hand.add(c);
    }
}
