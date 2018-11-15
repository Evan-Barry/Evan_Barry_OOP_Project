import java.util.ArrayList;

public class Player {

    private String type;
    private String name;
    private ArrayList<Card> hand = new ArrayList<>();//array list of cards, the hand

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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    /*public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }*/

    public Player(String name, String type)
    {
        setName(name);
        setType(type);
    }

    public void hit(Card c)
    {
        hand.add(c);
    }
}
