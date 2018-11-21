/**
 * Represents a Card
 */
public class Card {

    /**
     * The suit of the card
     */
    private String suit;
    /**
     * The number value of the card
     */
    private String value;

    /**
     * Gets the suit of this card
     * @return this card's suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * Gets the number value of this card
     * @return this card's value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Changes the suit of this card
     * @param suit This card's new suit
     */
    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    /**
     * Changes the value of this card
     * @param value This card's new value
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * Returns a string containing the value and suit of this card
     * @return The string form of this card's name
     */
    public String toString()
    {
        return getValue() + " of " + getSuit();
    }

    /**
     * Creates a new Card with the given suit and value
     * @param suit This card's suit
     * @param value This card's value
     */
    public Card(int suit, int value)
    {
        switch (suit)
        {
            case 0:
                setSuit("Clubs");
                break;
            case 1:
                setSuit("Spades");
                break;
            case 2:
                setSuit("Hearts");
                break;
            case 3:
                setSuit("Diamonds");
                break;
        }

        switch (value)
        {
            case 0:
                setValue("Ace");
                break;
            case 1:
                setValue("Two");
                break;
            case 2:
                setValue("Three");
                break;
            case 3:
                setValue("Four");
                break;
            case 4:
                setValue("Five");
                break;
            case 5:
                setValue("Six");
                break;
            case 6:
                setValue("Seven");
                break;
            case 7:
                setValue("Eight");
                break;
            case 8:
                setValue("Nine");
                break;
            case 9:
                setValue("Ten");
                break;
            case 10:
                setValue("Jack");
                break;
            case 11:
                setValue("Queen");
                break;
            case 12:
                setValue("King");
                break;

        }
    }
}
