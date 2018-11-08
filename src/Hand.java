import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand = new ArrayList<Card>();

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }

    public String toString()
    {
        String output = "";

        for(int k = 0; k < hand.size(); k++)
        {
            output += hand.get(k).getValue() + " of " + hand.get(k).getSuit() + "\n";
        }

        return output;
    }
}
