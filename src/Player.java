import java.util.ArrayList;

/**
 * Represents a player playing the game
 */
public class Player {
    /**
     * The type of the player
     * Can be human or dealer
     */
    private String type;

    /**
     * The name of the player
     * For human, the name is given by user. For dealer, name will be "Casino"
     */
    private String name;

    /**
     * Represents the hand of the player
     * Initially, player gets 2 and then can get more throughout game
     */
    private ArrayList<Card> hand = new ArrayList<>();//array list of cards, the hand

    /**
     * Tracks how many moves the player has made
     */
    private int movesMade;

    /**
     * Stores the sum of the cards in the players hand
     */
    private int handValue;

    /**
     * Gets the name of this player
     * @return this player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the type of this player
     * @return this player's type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Gets the hand of this player
     * @return this player's hand
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    /**
     * Gets the value of this player's hand
     * @return this player's hand value
     */
    public int getHandValue()
    {
        return handValue;
    }

    /**
     * Gets the amount of moves this player makes
     * @return this player's amount of moves made
     */
    public int getMovesMade()
    {
        return movesMade;
    }

    /**
     * Changes the name of this player
     * @param name This player's new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Changes the type of this player
     * @param type This player's new type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Changes the amount of moves made by this player
     * @param movesMade This player's new amount of moves made
     */
    public void setMovesMade(int movesMade)
    {
        this.movesMade = movesMade;
    }

    /**
     * Change the hand value of this player
     * @param handValue This player's new hand value
     */
    public void setHandValue(int handValue)
    {
        this.handValue = handValue;
    }

    /**
     * Create a new player with the given name and type
     * @param name The name of the player
     * @param type The type of the player
     */
    public Player(String name, String type)
    {
        setName(name);
        setType(type);
        setMovesMade(0);
        setHandValue(0);
    }


    /**
     * Adds a card to this player's hand
     * @param c The card to be added to this player's hand
     */
    public void hit(Card c)
    {
        hand.add(c);
    }
}
